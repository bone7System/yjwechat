package com.yj.domain.order.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.order.OrderDto;
import com.yj.pojo.order.OrderDtoS;
import com.yj.pojo.order.OrderTakeDto;
import com.yj.pojo.order.OrderUpdateDtoU;

import javax.validation.Valid;

public interface OrderService {
    ReSult addOrder( OrderDto dto, UserDetail user);

    ReSult updateOrder(OrderUpdateDtoU dto, UserDetail user) throws YjException;

    ReSult deleteOrder(Long id, UserDetail user) throws YjException;

    ReSult addDelivery( OrderTakeDto dto, UserDetail user) throws YjException;

    void updateOrderStatus(Long lydh, Long client) throws YjException;

    ReSult addReDelivery(Long jhid, UserDetail user) throws YjException;

    ReSult searchOrder(OrderDtoS dto, UserDetail user);
}
