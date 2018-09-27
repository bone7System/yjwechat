package com.yj.domain.report.service;

import com.google.common.collect.Maps;
import com.yj.domain.common.service.NativeSqlService;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.report.KcDto;
import com.yj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportServiceImpl implements  ReportService{
    @Autowired
    private NativeSqlService nativeSqlService;
    @Override
    public ReSult searchStore(KcDto kcDto, Pageable pageable, UserDetail user) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select c.*,d.cw,d.count from erp_commodity c\n" +
                "inner join erp_commodity_detail d on c.id = d.spid  where ");
        Map map= Maps.newHashMap();
        sqlBuffer.append(" c.client=:client");
        map.put("client",user.getClient());

        if(!StringUtils.isEmpty(kcDto.getSpbm())){
            sqlBuffer.append(" and c.spbm like :spbm");
            map.put("spbm","%"+kcDto.getSpbm()+"%");
        }

        if(!StringUtils.isEmpty(kcDto.getDj())){
            sqlBuffer.append(" and c.dj like :dj");
            map.put("dj","%"+kcDto.getDj()+"%");
        }

        if(!StringUtils.isEmpty(kcDto.getGg())){
            sqlBuffer.append(" and c.spbm like :gg");
            map.put("gg","%"+kcDto.getGg()+"%");
        }

        if(!StringUtils.isEmpty(kcDto.getSbmc())){
            sqlBuffer.append(" and c.spmc like :spmc");
            map.put("spmc","%"+kcDto.getSbmc()+"%");
        }

        if(!StringUtils.isEmpty(kcDto.getSppp())){
            sqlBuffer.append(" and c.sppp like :sppp");
            map.put("sppp","%"+kcDto.getSppp()+"%");
        }

         Page page=
              nativeSqlService.findBysql(sqlBuffer.toString(),pageable,map);

        return ReSult.success(page);
    }
}
