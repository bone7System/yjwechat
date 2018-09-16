package com.yj.web;

import com.yj.domain.customer.service.CustomerService;
import com.yj.domain.order.service.OrderService;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.customer.*;
import com.yj.pojo.order.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@Api(value = "/order", tags = "order", description = "订单")
public class OrderApi {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "/order/add", nickname = "添加订单", notes = "添加订单")
    @RequestMapping(value = "/order/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'customer:add')")
    ReSult addCustomer(@RequestBody @Valid OrderDto dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return orderService.addOrder(dto,user);
    }

}
