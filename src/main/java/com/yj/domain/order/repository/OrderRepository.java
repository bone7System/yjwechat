package com.yj.domain.order.repository;

import com.yj.domain.image.model.Image;
import com.yj.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
