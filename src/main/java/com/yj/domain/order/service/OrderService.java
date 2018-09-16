package com.yj.domain.order.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.order.OrderDto;

import javax.validation.Valid;

public interface OrderService {
    ReSult addOrder( OrderDto dto, UserDetail user);
}
