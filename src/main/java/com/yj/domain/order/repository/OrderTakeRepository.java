package com.yj.domain.order.repository;

import com.yj.domain.order.model.Order;
import com.yj.domain.order.model.OrderTake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTakeRepository extends JpaRepository<OrderTake,Long>{

}
