package com.yj.domain.price.repository;

import com.yj.domain.price.model.Price;
import com.yj.domain.price.model.TjPriceHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TjPriceHeadRepository extends JpaRepository<TjPriceHead,Long>{

}
