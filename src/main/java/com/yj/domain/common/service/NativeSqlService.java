package com.yj.domain.common.service;

import com.google.common.collect.Lists;
import com.yj.domain.order.model.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.LongSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NativeSqlService {
    @Autowired
    private EntityManager entityManager;


    public <T> T findOne(String sql,Class c,Map<String,Object> map){

        Query query =entityManager.createNativeQuery(sql,c);
        if(map!=null){
            map.forEach((k,v)->{
                if(v instanceof java.util.Date){
                    query.setParameter(k, (Date) v, TemporalType.DATE);
                    return;
                }
                query.setParameter(k,v);
            });
        }

        List list= query.getResultList();

        return list!=null && list.size()>0? (T) list.get(0) :null;
    }


    public List findList(String sql,Class c,Map<String,Object> map){

        Query query =entityManager.createNativeQuery(sql,c);
        if(map!=null){
            map.forEach((k,v)->{
                if(v instanceof java.util.Date){
                    query.setParameter(k, (Date) v, TemporalType.DATE);
                    return;
                }
                query.setParameter(k,v);
            });
        }

        List list= query.getResultList();

        return list;
    }


    public Page findBysql(String sql, Pageable pageable, Class c,Map<String,Object> map){
        //获取count
        Integer count=getCount(sql,map);
        if(count<=0){
            return PageableExecutionUtils.getPage(Lists.newArrayList(),pageable,()->{return 0;});
        }
        Query query =entityManager.createNativeQuery(sql,c);
        if(map!=null){
            map.forEach((k,v)->{
                if(v instanceof java.util.Date){
                    query.setParameter(k, (Date) v, TemporalType.DATE);
                    return;
                }
                query.setParameter(k,v);
            });
        }
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List list= query.getResultList();

        return PageableExecutionUtils.getPage(list,pageable,()->{return count;});
    }

    private Integer getCount(String sql, Map<String,Object> map){

        Pattern p=Pattern.compile("(?<=select)[\\s\\S]*?(?=from)");
        Matcher m=p.matcher(sql);
        if(m.find()){

            sql=sql.replace(m.group()," count(1) ");
            System.out.println(sql);
        }
        Query query= entityManager.createNativeQuery(sql);
        if(map!=null){
           map.forEach((k,v)->{
               if(v instanceof java.util.Date){
                   query.setParameter(k, (Date) v, TemporalType.DATE);
                   return;
               }
               query.setParameter(k,v);
           });
        }
        List<BigInteger> list= query.getResultList();

        return list.get(0).intValue();
    }



}
