package com.yj.domain.commondity.repository;

import com.yj.domain.commondity.model.ErpCommodityPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ErpCommodityPriceRepository extends JpaRepository<ErpCommodityPriceEntity, Integer> {

    @Modifying
    @Query(value = "update erp_commodity_price set del_flag= ?1 where spxqid in (" +
            " select id from erp_commodity_detail a where a.spid in ?2" +
            ")", nativeQuery = true)
    int updateDelFlag(Integer status,Integer[] ids);


}
