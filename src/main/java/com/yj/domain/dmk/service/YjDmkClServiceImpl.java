package com.yj.domain.dmk.service;


import com.yj.domain.dmk.model.YjDmkClEntity;
import com.yj.domain.dmk.repository.YjDmkClRepository;
import com.yj.utils.JpaUtil;
import com.yj.utils.NHCollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class YjDmkClServiceImpl implements YjDmkClService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ApplicationContext context;
    @Autowired
    private YjDmkClRepository clRepository;



    @CacheEvict(value = "dictionaries",allEntries = true)
    @Override
    public Integer deleteMulti(Integer[] pkids) {
        for (Integer pkid:pkids) {
            clRepository.deleteById(pkid);
        }
        return null;
    }
    @CacheEvict(value = "dictionaries",allEntries = true)
    @Override
    public Integer deleteByDmbzAndDm(YjDmkClEntity dto) {
        clRepository.deleteByDmbzAndDm(dto.getDmbz(),dto.getDm());
        return 1;
    }

    @Cacheable(value = "dictionaries",key = "#p0")
    @Override
    public List<Map<String, Object>> getFdmListByFflid(String  dmbz) {
        String sql="SELECT t.pkid as value,t.mc as label  FROM yj_dmk_cl t left join yj_dmk_fl t1 on t1.DMBZ=t.DMBZ where t1.dmbz="+dmbz;
        List<Map<String,Object>> list=context.getBean(JpaUtil.class).list(sql,null);
        return list;
    }
    @CacheEvict(value = "dictionaries",allEntries = true)
    @Override
    public Integer operateStatus(Integer[] pkids, String zt) {
        String sql="update yj_dmk_cl  set ZT='"+zt+"' where  "+NHCollectionUtils.buildSqlInNumber1000(pkids,"pkid") ;
        context.getBean(JpaUtil.class).update(sql,null);
        return 1;
    }

    @Cacheable(value = "dictionaries",key = "#p0+'_row'")
    @Override
    public YjDmkClEntity findByDm(String dm) {
        System.out.println(1111);
        return clRepository.findByDm(dm);
    }
}
