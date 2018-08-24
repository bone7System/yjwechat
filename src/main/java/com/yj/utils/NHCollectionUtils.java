package com.yj.utils;

import java.util.*;

/**
 * java容器集合类工具，主要负责操作一些List，Map，Set等对象
 * @author 时文杰
 *
 */
public class NHCollectionUtils {
	/**
	 * 将ListMap转成ListString
	 * @param list
	 * @param keyStr
	 * @return
	 */
	public static List<String> cloneListToList(List<Map<String, Object>> list,
			String keyStr) {
		List<String> result=new ArrayList<String>();
		for(Map<String,Object> map:list){
			result.add((map.get(keyStr)==null?null:""+map.get(keyStr)));
		}
		return result;
	}
	public static List<String> cloneListToList(List<Map<String,Object>> list,String... keyStr){
		if(keyStr.length==0){
			return null;
		}
		List<String> result=new ArrayList<String>();
		for(Map<String,Object> map:list){
			String keyString="";
			for(String str:keyStr){
				keyString+=(""+map.get(str));
			}
			result.add(keyString);
		}
		return result;
	}
	/**
	 * 将一个listString中的各值拼成字符串，使用“,”分割开
	 * @param list
	 * @return
	 */
	public static String cloneListToString(List<String> list) {
		StringBuffer sb=new StringBuffer();
		for(String str:list){
			sb.append(",");
			sb.append(str);
		}
		return sb.substring(1);
	}
	public static String[] cloneListToArray(List<Map<String,Object>> temp,String keyStr){
		if(temp==null){
			return null;
		}
		String[] result=new String[temp.size()];
		for(int i=0;i<temp.size();i++){
			result[i]=(String) temp.get(i).get(keyStr);
		}
		return result;
	}
	/**
	 *  这个方法用用来讲一个字符数字转换成sql  in 语句可以使用的字符串
	 * 形式'001','002','003','004'
	 * 如果结果为空，则返回一个uuid以保证sql语句正常执行
	 * @param keys
	 * @return
	 */
	public static String buildSqlInStr(String[] keys){
		String result=null;
		StringBuffer sb=new StringBuffer();
		if(keys!=null && keys.length!=0){
			for(String temp:keys){
				sb.append("'"+temp+"',");
			}
		}
		if(sb.length()>0){
			result=sb.substring(0, sb.length()-1);
		}else{
			result="'"+UUID.randomUUID().toString()+"'";
		}
		return result;
	}
	/**
	 * 这方法用来将list转化唱map，如果keyStr获得的值有多个，则对应textStr，使用regExp分隔符拼起来保存
	 * @param list
	 * @param keyStr
	 * @param valueStr
	 * @return 
	 * @return
	 */
	public static Map<String, Object> cloneListToMap(List<Map<String, Object>> list,String keyStr, String valueStr) {
		Map<String,Object> result=new HashMap<String,Object>();
		for(Map<String,Object> map:list){
			String key = (String) map.get(keyStr);
			if(key == null){
				continue;
			}
			result.put(key, map.get(valueStr));
		}
		return result;
	}
	/**
	 *  这个方法用用来讲一个字符数字转换成sql  in 语句可以使用的字符串
	 * 形式'001','002','003','004'
	 * 如果结果为空，则返回一个uuid以保证sql语句正常执行
	 * @param keys
	 * @return
	 */
	public static String buildSqlInStr(List<String> keys){
		String result=null;
		StringBuffer sb=new StringBuffer();
		if(keys!=null && keys.size()!=0){
			for(String temp:keys){
				sb.append("'"+temp+"',");
			}
		}
		if(sb.length()>0){
			result=sb.substring(0, sb.length()-1);
		}else{
			result="'"+UUID.randomUUID().toString()+"'";
		}
		return result;
	}
	/**
	 * 这个方法用来将List<Map<String,Object>>中的某一列使用","拼接成字符串
	 * @param cl
	 * @param keyStr 列名
	 * @return
	 */
	public static String buildCutStr(List<Map<String, Object>> cl, String keyStr) {
		StringBuffer result=new StringBuffer();
		for(Map<String,Object> m:cl){
			String value=","+m.get(keyStr);
			result.append(value);
		}
		return result.substring(1).toString();
	}
	/**
	 * 
	 * @param keys
	 * @param columnName
	 * @return
	 */
	public static String buildSqlInStr1000(List<String> keys,String columnName){
		int a=1000;
		if(keys.size()==0){
			return "("+columnName+" in ('"+UUID.randomUUID().toString()+"'))";
		}
		StringBuffer result=new StringBuffer();
		int size=keys.size();
		int k=size/a;
		int l=size%a;
		for(int i=0;i<k;i++){
			result.append("or "+columnName+" in (");
			for(int j=0;j<a-1;j++){
				result.append("'"+keys.get(i*a+j)+"',");
			}
			result.append("'").append(keys.get(i*a+a-1)).append("'").append(")");
		}
		result.append("or "+columnName+" in (");
		for(int i=0;i<l-1;i++){
			result.append("'"+keys.get(k*a+i)+"',");
		}
		result.append("'").append(keys.get(k*a+l-1)).append("'").append(")");
		return "("+result.substring(2)+")";
	}
	
	public static String buildSqlInStr1000(String[] keys,String columnName){
		int a=1000;
		if(keys.length==0){
			return "("+columnName+" in ('"+UUID.randomUUID().toString()+"'))";
		}
		StringBuffer result=new StringBuffer();
		int size=keys.length;
		int k=size/a;
		int l=size%a;
		for(int i=0;i<k;i++){
			result.append("or "+columnName+" in (");
			for(int j=0;j<a-1;j++){
				result.append("'"+keys[i*a+j]+"',");
			}
			result.append("'").append(keys[i*a+a-1]).append("'").append(")");
		}
		result.append("or "+columnName+" in (");
		for(int i=0;i<l-1;i++){
			result.append("'"+keys[k*a+i]+"',");
		}
		result.append("'").append(keys[k*a+l-1]).append("'").append(")");
		return "("+result.substring(2)+")";
	}
	
	/**
	 * 
	 * @param keys
	 * @param columnName
	 * @return
	 */
	public static String buildSqlInNumber1000(List<String> keys,String columnName){
		int a=1000;
		if(keys.size()==0){
			return "("+columnName+" in ('"+UUID.randomUUID().toString()+"'))";
		}
		StringBuffer result=new StringBuffer();
		int size=keys.size();
		int k=size/a;
		int l=size%a;
		for(int i=0;i<k;i++){
			result.append("or "+columnName+" in (");
			for(int j=0;j<a-1;j++){
				result.append(keys.get(i*a+j)+",");
			}
			result.append(keys.get(i*a+a-1)).append(")");
		}
		result.append("or "+columnName+" in (");
		for(int i=0;i<l-1;i++){
			result.append(keys.get(k*a+i)+",");
		}
		result.append(keys.get(k*a+l-1)).append(")");
		return "("+result.substring(2)+")";
	}
	
	public static String buildSqlInNumber1000(String[] keys,String columnName){
		int a=1000;
		if(keys.length==0){
			return "("+columnName+" in ('"+UUID.randomUUID().toString()+"'))";
		}
		StringBuffer result=new StringBuffer();
		int size=keys.length;
		int k=size/a;
		int l=size%a;
		for(int i=0;i<k;i++){
			result.append("or "+columnName+" in (");
			for(int j=0;j<a-1;j++){
				result.append(keys[i*a+j]+",");
			}
			result.append(keys[i*a+a-1]).append(")");
		}
		result.append("or "+columnName+" in (");
		for(int i=0;i<l-1;i++){
			result.append(keys[k*a+i]+",");
		}
		result.append(keys[k*a+l-1]).append(")");
		return "("+result.substring(2)+")";
	}

	public static String buildSqlInNumber1000(Integer[] keys,String columnName){
		int a=1000;
		if(keys.length==0){
			return "("+columnName+" in ('"+UUID.randomUUID().toString()+"'))";
		}
		StringBuffer result=new StringBuffer();
		int size=keys.length;
		int k=size/a;
		int l=size%a;
		for(int i=0;i<k;i++){
			result.append("or "+columnName+" in (");
			for(int j=0;j<a-1;j++){
				result.append(keys[i*a+j]+",");
			}
			result.append(keys[i*a+a-1]).append(")");
		}
		result.append("or "+columnName+" in (");
		for(int i=0;i<l-1;i++){
			result.append(keys[k*a+i]+",");
		}
		result.append(keys[k*a+l-1]).append(")");
		return "("+result.substring(2)+")";
	}

	
	public static String cloneArrayToStr(String[] errorColumnName) {
		if(errorColumnName==null){
			return null;
		}
		StringBuffer result=new StringBuffer();
		for(String str:errorColumnName){
			result.append(",").append(str);
		}
		return result.substring(1);
	}

	public static Object cloneListToString(List<Map<String, Object>> list, String keyStr) {
		StringBuffer sb=new StringBuffer("");
		if(list==null){
			return null;
		}
		for(Map<String,Object> map:list){
			sb.append(","+map.get(keyStr));
		}
		if(sb.length()>0){
			return sb.substring(1);
		}else{
			return sb.toString();
		}
		
	}
	/**
	 * 使用变参将一个list变成Map,map的key使用feStrs变参按照顺序取值并拼接起来，value是list中的map
	 * @param list
	 * @param feStrs
	 * @return
	 */
	public static Map<String, Map<String,Object>> cloneListToMap(List<Map<String,Object>> list,String... feStrs) {
		Map<String,Map<String,Object>> result=new HashMap<String,Map<String,Object>>();
		for(Map<String,Object> map:list){
			String featureStr="";
			for(String str:feStrs){
				featureStr+=map.get(str);
			}
			result.put(featureStr, map);
		}
		return result;
	}
	public static Map<String, Map<String,Object>> cloneListToMapWithException(List<Map<String,Object>> list,String... feStrs) throws Exception {
		Map<String,Map<String,Object>> result=new HashMap<String,Map<String,Object>>();
		for(Map<String,Object> map:list){
			String featureStr="";
			for(String str:feStrs){
				featureStr+=map.get(str);
			}
			if(result.get(featureStr)!=null){//featureStr已经在map中存在则报错
				throw new Exception(featureStr);
			}
			result.put(featureStr, map);
		}
		return result;
	}
	/**
	 * 将list转化成Map<String,Map<String,Object>>形式
	 * @param list
	 * @param outerKeyStr 外层map的Key取值str，如果取出空值则抛出NullPointerException
	 * @param innerKeyStr 内层map的key取值str，如果取出空值则抛出NullPointerException
	 * @param valueStr 内层map的value取值str，如果取出空值则抛出NullPointerException
	 * @return
	 */
	public static Map<String, Map<String, Object>> cloneListToBigMap(List<Map<String, Object>> list, String outerKeyStr, String innerKeyStr,String valueStr) {
		Map<String,Map<String,Object>> result=new HashMap<String,Map<String,Object>>();
		for(Map<String,Object> map:list){
			String outerKey=(String)map.get(outerKeyStr);
			if(outerKey==null){
				throw new NullPointerException();
			}
			Map<String,Object> tmp=result.get(outerKey);
			if(tmp==null){
				tmp=new HashMap<String,Object>();
				result.put(outerKey, tmp);
			}
			String innerKey=(String)map.get(innerKeyStr);
			Object value=map.get(valueStr);
			if(innerKey==null || value==null){
				throw new NullPointerException();
			}
			tmp.put(innerKey, value);
		}
		return result;
	}
	public static Map<String, List<Map<String, Object>>> cloneListToMapList(List<Map<String, Object>> list, String key) {
		Map<String,List<Map<String,Object>>> result=new HashMap<String,List<Map<String,Object>>>();
		for(Map<String,Object> m:list){
			String keystr=(String)m.get(key);
			List<Map<String,Object>> children=result.get(keystr);
			if(children==null){
				children=new ArrayList<Map<String,Object>>();
				result.put(keystr, children);
			}
			children.add(m);
		}
		return result;
	}
	
	public static void MapListMapPush(Map<String,List<Map<String,Object>>> mapper,String key,Map<String,Object> map){
		List<Map<String,Object>> list=mapper.get(key);
		if(list==null){
			list=new ArrayList<Map<String,Object>>();
			mapper.put(key, list);
		}
		list.add(map);
	}
	
}
