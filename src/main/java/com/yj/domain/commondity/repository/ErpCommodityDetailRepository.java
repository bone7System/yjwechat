package com.yj.domain.commondity.repository;

import com.yj.domain.commondity.model.ErpCommodityDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ErpCommodityDetailRepository extends JpaRepository<ErpCommodityDetailEntity, Long> {

    @Modifying
    @Query(value = "update erp_commodity_detail set del_flag= ?1 where spid in ?2" , nativeQuery = true)
    int updateDelFlag(Integer status,Integer[] ids);

    /**
     * 获取商品数量大于0的商品
     * @param id
     * @param count
     * @return
     */
    List<ErpCommodityDetailEntity> findBySpidAndCountGreaterThan(Integer id,Float count);

    ErpCommodityDetailEntity findBySpidAndCwAndClient(Long spid,Long cw,Long client);

    @Query(
            value = "select sum(count) from erp_commodity_detail where spid =:spid",nativeQuery = true
    )
    BigDecimal getSumCountBySpid(@Param("spid") Long spid);

}
