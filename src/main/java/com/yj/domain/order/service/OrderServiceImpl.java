package com.yj.domain.order.service;

import com.yj.domain.order.repository.OrderDetailRepository;
import com.yj.domain.order.repository.OrderRepository;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.order.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements  OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public ReSult addOrder(OrderDto dto, UserDetail user) {


        return null;
    }
}
