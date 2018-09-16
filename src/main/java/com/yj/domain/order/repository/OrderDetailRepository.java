package com.yj.domain.order.repository;

import com.yj.domain.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long>{

}
