package com.yj.domain.order.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.order.*;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    ReSult addOrder( OrderDto dto, UserDetail user);

    ReSult updateOrder(OrderUpdateDtoU dto, UserDetail user) throws YjException;

    ReSult deleteOrder(Long id, UserDetail user) throws YjException;

    ReSult addDelivery( OrderTakeDto dto, UserDetail user) throws YjException;

    void updateOrderStatus(Long lydh, Long client) throws YjException;

    ReSult addReDelivery(Long jhid, UserDetail user) throws YjException;

    ReSult searchOrder(Long dto, UserDetail user);

    ReSult searchOrder(OrderDtoS dto, Pageable pageable, UserDetail user);

    ReSult searchOrderDetail(OrderDetailDtoS dto, Pageable pageable, UserDetail user);

}
