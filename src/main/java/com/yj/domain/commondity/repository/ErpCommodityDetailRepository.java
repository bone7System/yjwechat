package com.yj.domain.commondity.repository;

import com.yj.domain.commondity.model.ErpCommodityDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ErpCommodityDetailRepository extends JpaRepository<ErpCommodityDetailEntity, Integer> {

    @Modifying
    @Query("update erp_commodity_detail set del_flag= ?1 where spid in ?2")
    int updateDelFlag(Integer status,Integer[] ids);


}
