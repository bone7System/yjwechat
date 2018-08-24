package com.yj.domain.common.service;

import com.yj.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonSqlUtils {
    private static Logger logger = LoggerFactory.getLogger(CommonSqlUtils.class);

    private static Map<String,Object> tableSqlMap=new HashMap<>();
    private static Map<String,Object> selectCacheMap=new HashMap<>();



    /**
     * 获取列表的sq语句
     * @param filters
     * @param searchTextMap
     * @param searchParams
     * @param sqlParams
     * @param sign
     * @return
     * @throws Exception
     */
    public static String getTableSql(Map<String, Object> filters, Map<String, Object> searchTextMap, Map<String, Object> searchParams, Map<String, Object> sqlParams, String sign) throws Exception {
        String sql="";
        try {
            sql=(String) tableSqlMap.get(sign);
            if(NHStringUtils.isEmpty(sql)) {
                Map<String, Object> params = NHDataSourceXmlReader.read("table",sign);
                sql=(String) params.get("sql");
                tableSqlMap.put(sign, sql);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new Exception("列表未获取到对应的查询语句，请联系管理员！");
        }
        try {
            StringBuffer sqlStr=new StringBuffer("");
            //filters:根据列表头部上面多选操作的值进行排序
            sqlStr.append(buildFilterSql(filters));
            //searchTextMap:根据列表头部上面输入框的值进行排序
            sqlStr.append(buildSearchTextMapSql(searchTextMap));
            //searchParams:自己定义的搜索条件
            sqlStr.append(NHSqlUtils.buildSearchSql(searchParams));
            //sqlParams:在sql语句中的参数
            sql=NHSqlUtils.buildSqlParams(sql,sqlParams);
            String runSql="select * from ("+sql+") st where 1=1 "+sqlStr.toString();
            return runSql;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("数据库操作异常",e);
        }
    }

    private static String buildFilterSql(Map<String, Object> filters) {
        StringBuffer orderSql = new StringBuffer("");
        for (String columnKey : filters.keySet()) {
            List<String> values=(List) filters.get(columnKey);
            if(values!=null && values.size()>0){
                orderSql.append(" and ("+NHCollectionUtils.buildSqlInStr1000(values, columnKey)+") ");
            }
        }
        return orderSql.toString();
    }
    private static String buildSearchTextMapSql(Map<String, Object> searchTextMap) throws Exception {
        StringBuffer orderSql = new StringBuffer("");
        for (String columnKey : searchTextMap.keySet()) {
            String value=(String) searchTextMap.get(columnKey);
            if(NHStringUtils.isEmpty(value)){
                continue;
            }
            NHRegexUtils.regexNoSpecialChar(value);
            orderSql.append(" and "+columnKey+" like '%"+value+"%'");
        }
        return orderSql.toString();
    }

    public static Map<String,Object> getCascaderSql(String sign) throws Exception {
        Map<String, Object> params = (Map<String, Object>) selectCacheMap.get(sign);
        if(params==null){
            try {
                params = NHDataSourceXmlReader.read("select", sign);
                if(params==null) {
                    throw new Exception("未获取到配置参数，请联系管理员！");
                }
                selectCacheMap.put(sign, params);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("未获取到配置参数，请联系管理员！");
            }
        }
        return params;
    }
}
