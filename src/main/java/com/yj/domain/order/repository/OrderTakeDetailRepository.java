package com.yj.domain.order.repository;

import com.yj.domain.order.model.OrderTakeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderTakeDetailRepository extends JpaRepository<OrderTakeDetail,Long>{
    List<OrderTakeDetail> findByTkidAndClient(Long tkid,Long client);
}
