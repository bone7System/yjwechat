package com.yj.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NHSqlUtils {
	
	/**
	* 拼凑查询参数
	 * 查询参数类型：SQL(sql语句)、S(字符串)、D(时间)、N(数字)、G(组)
	 * 数据查询方式：EQ(等于)、NEQ(不等于)、GT(大于)、GTEQ(大于等于)、LT(小于)、LTEQ(小于等于)、LK(模糊查询)、IN(IN查询)、
	 * 类型方式对应： SQL(sql语句)-无
	 *            G(组)-无
	 * 			  S(字符串)-EQ(等于)、NEQ(不等于)、LK(模糊查询)、IN(IN查询)
	 * 			  N(数字)-EQ(等于)、NEQ(不等于)、GT(大于)、GTEQ(大于等于)、LT(小于)、LTEQ(小于等于)、IN(IN查询)
	 *            D(时间)-还未实现
	 * @param searchParams
	 * @return
	 * @throws Exception 
	 */
	public static String buildSearchSql(Map<String, Object> searchParams) throws Exception {
		if(searchParams==null) {
			return "";
		}
		return buildSearchParamsSql(searchParams,"and");
	}
	
	/**
	 * 拼凑查询参数
	 * 查询参数类型：SQL(sql语句)、S(字符串)、D(时间)、N(数字)、G(组)
	 * 数据查询方式：EQ(等于)、NEQ(不等于)、GT(大于)、GTEQ(大于等于)、LT(小于)、LTEQ(小于等于)、LK(模糊查询)、IN(IN查询)、
	 * 类型方式对应： SQL(sql语句)-无
	 *            G(组)-无
	 * 			  S(字符串)-EQ(等于)、NEQ(不等于)、LK(模糊查询)、IN(IN查询)
	 * 			  N(数字)-EQ(等于)、NEQ(不等于)、GT(大于)、GTEQ(大于等于)、LT(小于)、LTEQ(小于等于)、IN(IN查询)
	 *            D(时间)-还未实现
	 * @param searchParams 参数
	 * @param linkMode 这个里面的查询条件的关联方式，主要有and和 or
	 * @return
	 * @throws Exception 
	 */
	private static String buildSearchParamsSql(Map<String, Object> searchParams,String linkMode) throws Exception {
		StringBuffer sqlStr = new StringBuffer("");
		for (String columnKey : searchParams.keySet()) {
			//先对columnKey做处理
			//前面必须使用s(字符串)、d(时间)、n(数字)、sql(sql语句)，默认s
			String key=columnKey.toString();
			if(!key.toUpperCase().startsWith("S_") && !key.toUpperCase().startsWith("D_") && !key.toUpperCase().startsWith("N_")
					&& !key.toUpperCase().startsWith("SQL_")){
				key="S_"+key;
			}
			//后面只能是eq(相等)、lk(模糊查询)、in(in查询),默认lk
			if(!key.toUpperCase().endsWith("_EQ") && !key.toUpperCase().endsWith("_LK") && !key.toUpperCase().endsWith("_IN") && !key.toUpperCase().endsWith("_NEQ")
					&& !key.toUpperCase().endsWith("_GT") && !key.toUpperCase().endsWith("_GTEQ") && !key.toUpperCase().endsWith("_LT") && !key.toUpperCase().endsWith("_LTEQ")){
				key=key+"_LK";
			}
			//获取类型和字段名
			String dataType=key.substring(0, key.indexOf("_")).toUpperCase();
			String action=key.substring(key.lastIndexOf("_")+1, key.length()).toUpperCase();
			String fieldKey=key.substring(key.indexOf("_")+1,key.lastIndexOf("_"));
			if(fieldKey.indexOf(",")<=-1) {
				fieldKey=NHStringUtils.translateString(fieldKey, NHStringUtils.TranslateType.hump_to_upper);
			}
			//如果是in查询，则默认dataType是s(字符串)
			Object obj=(Object) searchParams.get(columnKey);
			if(obj==null){
				continue;
			}
			if(obj instanceof Map || "G".equals(dataType.toUpperCase())){//如果值是Map对象,表示这个参数是一个组
				String res = buildSearchParamsSql((Map<String,Object>)obj,"or");
				if(NHStringUtils.isNotEmpty(res)) {
					res=res.trim().substring(2);
					sqlStr.append(" "+linkMode+" ("+res+") ");
				}
			}else if("SQL".equals(dataType.toUpperCase()) && !"".equals(obj)){//SQL语句类型
				//如果是sql语句,此时需要解密
				sqlStr.append(" "+linkMode+" ("+NHDesUtils.getInstance().decrypt((String)obj)+") ");
			}else if("S".equals(dataType.toUpperCase())){//字符串,字符串类型只能是IN、EQ。LK
				if(fieldKey.toUpperCase().indexOf(",")>-1) {//字段里面有逗号，则说明这个是使用多字段进行查询,结果只需要满足其中一个的值即可
					if(fieldKey.toUpperCase().startsWith("MULTIFIELD,")) {//如果字段已mulltiField,开头,则去掉这个开头的
						fieldKey=fieldKey.substring(11);
					}
					String[] fieldKeys=fieldKey.split(",");
					String str="";
					for (int i = 0; i < fieldKeys.length; i++) {
						str+="||';'||"+NHStringUtils.translateString(fieldKeys[i], NHStringUtils.TranslateType.hump_to_upper);
					}
					if(!"".equals(str)){
						sqlStr.append(" "+linkMode+" ("+str.substring(7)+" like '%"+obj+"%'"+")");
					}
				}else if("IN".equals(action)){
					List<String> values=(List<String>) obj;
					sqlStr.append(" "+linkMode+" "+NHCollectionUtils.buildSqlInStr1000(values, fieldKey)+" ");
				}else if("EQ".equals(action)){
					if(NHStringUtils.isEmpty((String)obj)){
						continue;
					}
					NHRegexUtils.regexNoSpecialChar(obj);//校验是否数据是否符合要求
					sqlStr.append(" "+linkMode+" "+fieldKey+" = '"+obj+"' ");
				}else if("NEQ".equals(action)){
					if(NHStringUtils.isEmpty((String)obj)){
						continue;
					}
					NHRegexUtils.regexNoSpecialChar(obj);//校验是否数据是否符合要求
					sqlStr.append(" "+linkMode+" "+fieldKey+" != '"+obj+"' ");
				}else{
					if(NHStringUtils.isEmpty((String)obj)){
						continue;
					}
					NHRegexUtils.regexNoSpecialChar(obj);//校验是否数据是否符合要求
					sqlStr.append(" "+linkMode+" "+fieldKey+" like '%"+obj+"%' ");
				}
			}else if("D".equals(dataType.toUpperCase())){//时间类型 
				
			}else if("N".equals(dataType.toUpperCase())){//数字类型类型 
				if("IN".equals(action)) {//IN查询
					List<Object> values=(List<Object>) obj;
					List<String> list=new ArrayList<>();
					for (Object obj2 : values) {
						if(obj2!=null) {
							list.add(obj2+"");
						}
					}
					sqlStr.append(" "+linkMode+" "+NHCollectionUtils.buildSqlInNumber1000(list, fieldKey)+" ");
				}else {
					String tempObj=obj+"";
					if(NHStringUtils.isEmpty(tempObj) || "null".equals(tempObj)){
						continue;
					}
					NHRegexUtils.regexNoSpecialChar(obj);//校验是否数据是否符合要求
					if("EQ".equals(action)){//等于
						sqlStr.append(" "+linkMode+" "+fieldKey+" = "+obj+" ");
					}else if("NEQ".equals(action)){//不等于
						sqlStr.append(" "+linkMode+" "+fieldKey+" != "+obj+" ");
					}else if("GT".equals(action)){//大于
						sqlStr.append(" "+linkMode+" "+fieldKey+" > "+obj+" ");
					}else if("GTEQ".equals(action)){//大于等于
						sqlStr.append(" "+linkMode+" "+fieldKey+" >= "+obj+" ");
					}else if("LT".equals(action)){//小于
						sqlStr.append(" "+linkMode+" "+fieldKey+" < "+obj+" ");
					}else if("LTEQ".equals(action)){//小于等于
						sqlStr.append(" "+linkMode+" "+fieldKey+" <= "+obj+" ");
					}
				}
			}
		}
		return sqlStr.toString();
	}

	public static String buildOrderSql(Map<String, Object> sorter) {
		String orderSql = "";
		//对排序参数进行处理
		if(sorter!=null && sorter.get("columnKey")!=null && sorter.get("order")!=null){
			String columnKey=(String) sorter.get("columnKey");
			String order=(String) sorter.get("order");
			order = "descend".equals(order)?"desc":"asc";
			orderSql = " order by "+columnKey+" "+order;
		}
		return orderSql;
	}
	
	
	/**
	 * @Title: buildSqlParams
	 * @Description: 处理在sql语句中的查询参数
	 * @author: yizhiqiang
	 * @date: 2018年3月9日 下午11:50:55
	 * @param: @param sql
	 * @param: @param sqlParams
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String buildSqlParams(String sql, Map<String, Object> sqlParams) {
		if(sqlParams!=null){
			for (String key : sqlParams.keySet()) {
				sql=sql.replace("#{"+key+"}", (String)sqlParams.get(key));
			}
		}
		return sql;
	}
}
