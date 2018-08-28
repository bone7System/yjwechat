package com.yj.utils;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Transactional
@Repository
public class JpaUtil{
    @PersistenceContext
    private EntityManager em;
    /**
     * 获取列表
     * sql示例：String hql = "select o.uuid,o.name from UserModel o where 1=1 and o.uuid=:uuid";
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String,Object>> list(String sql, Map<String, Object> params) {
        sql=sql.replace("\n"," ");
        Query query = em.createNativeQuery(sql);
        if (params != null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    /**
     * 获取分页数据
     * @param sql
     * @param params
     * @param pageable
     * @return
     */
    public Page<Map<String,Object>> page(String sql, Map<String, Object> params, Pageable pageable) {
        sql=sql.replace("\n"," ");
        Query query = em.createNativeQuery(sql);
        if (params != null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        if (pageable!=null && pageable.isPaged()) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> result = query.getResultList();
        Integer index=(int) pageable.getOffset();
        for (Map<String,Object> map : result){
            index++;
            map.put("rn",index);
        }

        /**
         * 生成获取总数的sql
         */
        String totalSql="select count(*) from ("+sql+")";
        Query cQuery = em.createNativeQuery(totalSql);
        if (params != null) {
            for (String key : params.keySet()) {
                cQuery.setParameter(key, params.get(key));
            }
        }
        return PageableExecutionUtils.getPage(result, pageable, ()-> executeCountQuery((TypedQuery<Long>)cQuery));
    }

    private static Long executeCountQuery(TypedQuery<Long> query) {
        Assert.notNull(query, "TypedQuery must not be null!");
        List<Long> totals = query.getResultList();
        Long total = 0L;
        for (Long element : totals) {
            total += element == null ? 0 : element;
        }
        return total;
    }

    public void update(String sql, Map<String,Object> params) {
        Query query = em.createNativeQuery(sql);
        if (params != null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        query.executeUpdate();
    }
}