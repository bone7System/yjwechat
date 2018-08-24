package com.yj.domain.dmk.service;


import com.yj.domain.dmk.model.YjDmkClEntity;
import com.yj.domain.dmk.model.YjDmkFlEntity;
import com.yj.domain.dmk.repository.YjDmkClRepository;
import com.yj.domain.dmk.repository.YjDmkFlRepository;
import com.yj.utils.JpaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class YjDmkFlServiceImpl implements YjDmkFlService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ApplicationContext context;
    @Autowired
    private YjDmkFlRepository flRepository;
    @Autowired
    private YjDmkClRepository clRepository;


    @Override
    public Integer insert(YjDmkFlEntity dto) {
        flRepository.save(dto);
        return 1;
    }

    @Override
    public Integer update(YjDmkFlEntity po) {
        Optional<YjDmkFlEntity> opt = flRepository.findById(po.getPkid());
        YjDmkFlEntity updatePO=opt.get();
        updatePO.setDmmc(po.getDmmc());
        updatePO.setDmms(po.getDmms());
        updatePO.setFflid(po.getFflid());
        updatePO.setPxh(po.getPxh());
        updatePO.setZt(po.getZt());
        return 1;
    }

    @Override
    public YjDmkFlEntity get(Integer id) {
        Optional<YjDmkFlEntity> opt = flRepository.findById(id);
        return opt.get();
    }

    @Override
    public Integer delete(Integer pkid) {
        flRepository.deleteById(pkid);
        return 1;
    }

    @Override
    public List<Map<String,Object>> queryClList(String dmbz) {
        String sql="select t.pkid,t.dm,t.mc,t.jc,t.pxh,t.bq,t.zt,t.fid,t1.dmbz,t1.dmmc ,t3.mc as fmc from YJ_DMK_CL t left join YJ_DMK_FL t1 on t1.dmbz=t.dmbz left join YJ_DMK_FL t2 on t2.pkid=t1.fflid  left join YJ_DMK_CL t3 on t3.dmbz=t2.dmbz and t3.pkid=t.fid where t.dmbz='"+dmbz+"' ";
        List<Map<String,Object>> list=context.getBean(JpaUtil.class).list(sql,null);
        return list;
    }

    @Override
    public Integer insertClMulti(List<YjDmkClEntity> dtos) {
        for (YjDmkClEntity dto:dtos) {
            clRepository.save(dto);
        }
        return 1;
    }


}
