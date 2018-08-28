package com.yj.domain.commondity.repository;

import com.yj.domain.commondity.model.ErpCommodityEntity;
import com.yj.pojo.commidity.ErpCommodityPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface ErpCommodityRepository extends JpaRepository<ErpCommodityEntity, Integer>,JpaSpecificationExecutor<ErpCommodityPojo> {

    @Query(value = "SELECT t.id as spid,t.spbm,t.spmc,t.spms,t.splxdm,t.sppp,t.mdid,t1.id as spxqid,t1.gg,t1.dj,t1.dwdm,t2.id as spjgid,t2.bzjg,t3.mc as dw,t4.mc as splx " +
            "from erp_commodity t " +
            "left join erp_commodity_detail t1 on t1.spid=t.id " +
            "left join erp_commodity_price t2 on t2.spxqid=t1.id " +
            "left join yj_dmk_cl t3 on t3.dmbz='DW' and t3.dm=t1.dwdm " +
            "left join yj_dmk_cl t4 on t4.dmbz='SPLX' and t4.dm=t.splxdm " +
            "where t.id=:spid", nativeQuery = true)
    Map<String,Object> findBySpid(@Param("spid") Integer spid);

    @Modifying
    @Query(value = "update erp_commodity set del_flag= ?1 where id in ?2", nativeQuery = true)
    int updateDelFlag(Integer status,Integer[] ids);
}
