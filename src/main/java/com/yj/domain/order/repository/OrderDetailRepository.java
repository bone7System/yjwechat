package com.yj.domain.order.repository;

import com.yj.domain.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long>{

    List<OrderDetail> findByOrderIdAndClient(Long orderId,Long client);
    List<OrderDetail> findByOrderIdAndClientAndStatusLessThan(Long orderId,Long client,Long status);

    OrderDetail findByOrderIdAndRownumAndClient(Long orderId,Long rownum,Long client);
}
