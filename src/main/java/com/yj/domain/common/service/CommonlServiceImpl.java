package com.yj.domain.common.service;


import com.yj.utils.JpaUtil;
import com.yj.utils.NHDataSourceXmlReader;
import com.yj.utils.NHSqlUtils;
import com.yj.utils.NHStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommonlServiceImpl implements CommonlService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationContext context;

    /**
     * Table列表分页查询
     * @param pagination
     * @param filters
     * @param sorter
     * @param searchTextMap
     * @param searchParams
     * @param sqlParams
     * @param sign
     * @return
     */
    @Override
    public Page<Map<String, Object>> queryPage(Map<String, Object> pagination, Map<String, Object> filters, Map<String, Object> sorter, Map<String, Object> searchTextMap, Map<String, Object> searchParams, Map<String, Object> sqlParams, String sign) throws Exception {
        Integer pageNum = pagination.get("page") == null ? 0 : (Integer) pagination.get("page");// 页数，第几页
        Integer pageSize = pagination.get("pageSize") == null ? Integer.MAX_VALUE : (Integer) pagination.get("pageSize");// 每一页的数量
        //1、获取查询需要的sql语句
        String sql=CommonSqlUtils.getTableSql(filters,searchTextMap,searchParams,sqlParams,sign);
        Sort sort = getOrders(sorter);
        Pageable pageable =new PageRequest(pageNum, pageSize , sort);
        Page<Map<String, Object>> page = context.getBean(JpaUtil.class).page(sql,null,pageable);
        return page;
    }

    /**
     * 查询级联数据
     * @param level
     * @param sign
     * @param cascaderValue
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> selectCascaderList(Integer level, String sign, String cascaderValue) throws Exception {
        Map<String, Object> params = CommonSqlUtils.getCascaderSql(sign);
        String sql = (String) params.get("sql");
        List<Map<String, Object>> fieldScarchList = (List<Map<String, Object>>) params.get("levels");
        boolean isLeaf = false;
        if (level == fieldScarchList.size()) {
            isLeaf = true;
        }
        // 1、获取这一级需要查询的数据的字段
        Map<String, Object> map = fieldScarchList.get(level - 1);
        String valueField = (String) map.get("valueField");
        String labelField = (String) map.get("labelField");
        // 排序
        String pxField = (String) map.get("pxField");
        // 2、获取需要查询的sql语句
        StringBuffer searchSql = new StringBuffer();
        searchSql.append("select * from (");

        if (pxField != null && !pxField.equals("")) {
            searchSql.append("select distinct  " + valueField + " as value," + labelField + " as label," + level
                    + " as curr_level," + pxField + " as pxh from (" + sql + ") bb");
        } else {
            searchSql.append("select distinct  " + valueField + " as value," + labelField + " as label," + level
                    + " as curr_level from (" + sql + ") cc");
        }
        if (NHStringUtils.isNotEmpty(cascaderValue)) {
            map = fieldScarchList.get(level - 2);
            String parentValueField = (String) map.get("valueField");
            searchSql.append(" where " + parentValueField + " = '" + cascaderValue + "'");
        }
        searchSql.append(") cc");
        if (pxField != null && !pxField.equals("")) {
            searchSql.append(" order by pxh");
        }
        // 3、查询数据
        List<Map<String, Object>> resultList = context.getBean(JpaUtil.class).list(searchSql.toString(),null);
        // 4、对查询的数据进行处理
        for (Map<String, Object> map4 : resultList) {
            map4.put("isLeaf", isLeaf);
            map4.put("currLevel", map4.get("curr_level"));
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> selectDataList(String sign, Map<String, Object> params, Map<String, Object> sqlParams) throws Exception {
        String sql = "";
        // 如果是代码库，则直接获取默认sql语句，否则去获取设置的sql语句
        if (sign.toUpperCase().startsWith("DMK_")) {
            String fl_sign = sign.substring(4);
            sql = "select DM as value,MC as label  from yj_dmk_cl t where IFNULL(t.ZT,'0')='1'  and  UPPER(DMBZ)=UPPER('"+fl_sign+"')  order by t.PXH,t.DM";
        } else {
            try {
                Map<String, Object> map = NHDataSourceXmlReader.read("select", sign);
                sql = (String) map.get("sql");
            } catch (Exception e1) {
                throw new Exception("列表未获取到对应的查询语句，请联系管理员！");
            }
        }
        sql=NHSqlUtils.buildSqlParams(sql,sqlParams);
        if(params!=null){
            sql="select * from ("+sql+") aa where 1=1 "+NHSqlUtils.buildSearchSql(params);
        }
        try {
            List<Map<String, Object>> resultList = context.getBean(JpaUtil.class).list(sql,null);
            return resultList;
        } catch (Exception e) {
            throw new Exception("下拉列表数据查询失败，请联系管理员！");
        }
    }

    @Override
    public List<Map<String, Object>> getTreeList(String sign, Map<String, Object> csMap,Map<String,Object> sqlParams) throws Exception {
        try {
            Map<String, Object> params = NHDataSourceXmlReader.read("tree", sign);
            String sql = (String) params.get("sql");
            sql=NHSqlUtils.buildSqlParams(sql,sqlParams);
            sql="select * from ("+sql+") tr where 1=1 "+NHSqlUtils.buildSearchSql(csMap);
            List<Map<String, Object>> list =context.getBean(JpaUtil.class).list(sql,null);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("数据库操作异常", e);
        }
    }

    /**
     * 获取排序
     * @param sorter
     * @return
     */
    private Sort getOrders(Map<String, Object> sorter) {
        Sort sort = null;
        if(sorter!=null && sorter.get("columnKey")!=null && sorter.get("order")!=null){
            String columnKey=(String) sorter.get("columnKey");
            String order=(String) sorter.get("order");
            if("descend".equals(order)){
                sort = new Sort(Sort.Direction.DESC, columnKey);
            }else{
                sort = new Sort(Sort.Direction.ASC, columnKey);
            }
        }
        return sort;
    }

    public List<Map<String, Object>> findChildren(Map<String, Object> map, List<Map<String, Object>> list, int level) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> it : list) {
            if ((map.get("KEY")).equals(it.get("PID"))) {
                if (map.get("children") == null) {
                    it.put("isLeaf", true);
                    it.put("level", level);
                    List<Map<String, Object>> children = findChildren(it, list, (level + 1));
                    map.put("children", children);
                } else {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> child = (List<Map<String, Object>>) map.get("children");
                    child.add(it);
                    it.put("isLeaf", true);
                    it.put("level", level);
                    List<Map<String, Object>> children = findChildren(it, list, (level + 1));
                    map.put("children", child);
                }
            }
        }
        result.add(map);
        return result;
    }
}
