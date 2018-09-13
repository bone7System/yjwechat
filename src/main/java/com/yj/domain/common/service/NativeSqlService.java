package com.yj.domain.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.function.LongSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NativeSqlService {
    @Autowired
    private EntityManager entityManager;

    public Page findBysql(String sql, Pageable pageable, Class c,Map<String,Object> map){
        //获取count

        Integer count=getCount(sql,map);

        Query query =entityManager.createNativeQuery(sql,c);
        if(map!=null){
            map.forEach((k,v)->{
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
               query.setParameter(k,v);
           });
        }
        List<BigInteger> list= query.getResultList();
        System.out.println(list.get(0).getClass());

        return list.get(0).intValue();


    }

    public static void main(String[] args) {
        String sql="select a,b from c where a in ( select ccc from b)";
        Pattern p=Pattern.compile("(?<=select)[\\s\\S]*?(?=from)");
        Matcher m=p.matcher(sql);
        if(m.find()){

            sql=sql.replace(m.group()," count(1) ");
            System.out.println(sql);
        }
    }
}
