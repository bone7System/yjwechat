package com.yj.common.exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.yj.utils.NHStringUtils;
import org.springframework.stereotype.Component;


@Component
public class NHExpHandleUtils {

	private static NHExpHandleUtils utils;
	
    @PostConstruct  
    public void init() {  
    	utils = this;  
    }
	
	/**
	 * 对操作数据库的异常统一进行处理
	 * MyBatisSystemException:MyBatis数据处理异常
	 * @param e
	 */
	public static void throwesException(Exception e) throws Exception {
		Throwable t=e.getCause();
		if(t==null){
			t=e;
		}
		if(t instanceof SQLException){
			SQLException sqle=(SQLException) t;
			int i=sqle.getErrorCode();
			String message=sqle.getMessage();
			if(i==1){
				String indexName=message.substring(message.indexOf("(")+1,message.indexOf(")"));
				if(indexName.contains(".")){
					indexName=indexName.substring(indexName.indexOf(".")+1);
				}
				String sql="select column_name from user_ind_columns t where t.INDEX_NAME='"+indexName+"'";
//				List<Map<String,Object>> list= utils.dao.selectListBySql(sql);
				List<Map<String,Object>> list= new ArrayList<>();
						String fields="";
				for(Map<String,Object> map:list){
					String columnName=(String)map.get("COLUMN_NAME");
					String fieldName=NHStringUtils.translateString(columnName, NHStringUtils.TranslateType.upper_to_hump);
					fields+=(","+fieldName);
				}
				throw new NHDBUniqueException(fields.substring(1));
			}else if(i==12899){
				int startIndex=message.indexOf("\"");
				int endIndex=message.lastIndexOf("\"");
				String columnToLongMsg=message.substring(startIndex,endIndex+1).replace("\"", "");
				String columnName=columnToLongMsg.substring(columnToLongMsg.lastIndexOf(".")+1);
				String fieldName=NHStringUtils.translateString(columnName, NHStringUtils.TranslateType.upper_to_hump);
				startIndex=message.indexOf("(");
				endIndex=message.indexOf(")");
				String maxLengthMsg=message.substring(startIndex+1,endIndex);
				String[] arr2=maxLengthMsg.split(",");
				String realLength=arr2[0].split(":")[1].trim();
				String maxLength=arr2[1].split(":")[1].trim();
				throw new Exception("dataToLongError["+fieldName+","+realLength+","+maxLength+"]");
			}else if(i==1400){//数据不能为null
				int startIndex=message.lastIndexOf(".");
				String str=message.substring(startIndex+1);
				String columnName=str.substring(str.indexOf("\"")+1,str.lastIndexOf("\""));
				String fieldName=NHStringUtils.translateString(columnName, NHStringUtils.TranslateType.upper_to_hump);
				throw new Exception("columnNotNull["+fieldName+"]");
			}else if(i==2292){ //违反了约束条件，查询到了已使用的子记录
				throw new Exception("businessLogicError[违反了约束条件，查询到了已使用的子记录！]");
			}else if(i==2291){//违反约束条件，未找到父项关键字
				throw new Exception("businessLogicError[违反约束条件，未找到父项关键字！]");
			}else{
				throw new Exception("businessLogicError[数据库异常，请联系管理员！]");
			}
		}else{//其他不知道是什么错误的异常，也直接抛出
			throw new Exception(t.getMessage());
		}
	}

}
