package com.yj.domain.price.repository;

import com.yj.domain.price.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price,Long>{
    /**
     * 商品历史价格
     * @param spbm
     * @param client
     * @return
     */
    List<Price> findBySpbmAndTypeAndClient(String spbm,Long type,Long client);

}
