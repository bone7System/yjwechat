package com.yj.domain.price.repository;

import com.yj.domain.price.model.Price;
import com.yj.domain.price.model.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceTypeRepository extends JpaRepository<PriceType,Long>{


    List<PriceType> findByClientOrClient(Long client, long l);
}
