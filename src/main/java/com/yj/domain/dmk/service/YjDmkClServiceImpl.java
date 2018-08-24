package com.yj.domain.dmk.service;


import com.yj.domain.dmk.model.YjDmkClEntity;
import com.yj.domain.dmk.repository.YjDmkClRepository;
import com.yj.utils.JpaUtil;
import com.yj.utils.NHCollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public Integer deleteMulti(Integer[] pkids) {
        for (Integer pkid:pkids) {
            clRepository.deleteById(pkid);
        }
        return null;
    }

    @Override
    public Integer deleteByDmbzAndDm(YjDmkClEntity dto) {
        clRepository.deleteByDmbzAndDm(dto.getDmbz(),dto.getDm());
        return 1;
    }

    @Override
    public List<Map<String, Object>> getFdmListByFflid(Integer fflid) {
        String sql="SELECT t.pkid as value,t.mc as label  FROM YJ_DMK_CL t left join YJ_DMK_FL t1 on t1.DMBZ=t.DMBZ where t1.PKID="+fflid;
        List<Map<String,Object>> list=context.getBean(JpaUtil.class).list(sql,null);
        return list;
    }

    @Override
    public Integer operateStatus(Integer[] pkids, String zt) {
        String sql="update YJ_DMK_CL  set ZT='"+zt+"' where  "+NHCollectionUtils.buildSqlInNumber1000(pkids,"pkid") ;
        context.getBean(JpaUtil.class).update(sql,null);
        return 1;
    }
}
