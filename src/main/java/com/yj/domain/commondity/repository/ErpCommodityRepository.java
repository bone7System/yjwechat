package com.yj.domain.commondity.repository;

import com.yj.domain.commondity.model.ErpCommodityEntity;
import com.yj.pojo.commidity.ErpCommodityPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ErpCommodityRepository extends JpaRepository<ErpCommodityEntity, Integer> {

    @Query(value = "SELECT t.id as spid,t.spbm,t.spmc,t.spms,t.splxdm,t.sppp,t.mdid,t1.id as spxqid,t1.gg,t1.dj,t1.dwdm,t1.spid,t2.id as spjgid,t2.bzjg,t2.spxqid " +
            "from erp_commodity t " +
            "left join erp_commodity_detail t1 on t1.spid=t.id " +
            "left join erp_commodity_price t2 on t2.spxqid=t1.id " +
            "where t.id=:spid", nativeQuery = true)
    ErpCommodityPojo findBySpid(@Param("spid") Integer spid);

    @Modifying
    @Query("update erp_commodity set del_flag= ?1 where id in ?2")
    int updateDelFlag(Integer status,Integer[] ids);
}
