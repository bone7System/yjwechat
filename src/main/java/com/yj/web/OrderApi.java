package com.yj.web;

import com.yj.domain.customer.service.CustomerService;
import com.yj.domain.order.service.OrderService;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.customer.*;
import com.yj.pojo.order.*;
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
    @PreAuthorize("hasPermission('', 'order:add')")
    ReSult addOrder(@RequestBody @Valid OrderDto dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return orderService.addOrder(dto,user);
    }

    @ApiOperation(value = "/order/update", nickname = "修改订单", notes = "修改订单")
    @RequestMapping(value = "/order/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'order:update')")
    ReSult updateOrder(@RequestBody @Valid OrderUpdateDtoU dto, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return orderService.updateOrder(dto,user);
    }


    @ApiOperation(value = "/order/delete", nickname = "删除订单", notes = "删除订单")
    @RequestMapping(value = "/order/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'order:delete')")
    ReSult addOrder(Long id, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return orderService.deleteOrder(id,user);
    }


    @ApiOperation(value = "/order-take/add", nickname = "添加订单交货单", notes = "添加订单交货单")
    @RequestMapping(value = "/order-take/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'order-take:add')")
    ReSult addDelivery(@RequestBody @Valid OrderTakeDto dto, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        ReSult reSult= orderService.addDelivery(dto,user);
        if(reSult.getCode().intValue()==200){
            orderService.updateOrderStatus(dto.getHead().getLydh(),user.getClient());
        }

        return reSult;
    }

    @ApiOperation(value = "/order-take/readd", nickname = "冲销交货单", notes = "冲销交货单")
    @RequestMapping(value = "/order-take/readd", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'order-take:add')")
    ReSult addReDelivery(Long jhid, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        ReSult reSult= orderService.addReDelivery(jhid,user);
        return reSult;
    }


    @ApiOperation(value = "/order/search-one", nickname = "查询单个订单", notes = "查询单个订单")
    @RequestMapping(value = "/order/search-one", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'order:search')")
    ReSult searchOrder(Long id, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        ReSult reSult= orderService.searchOrder(id,user);
        return reSult;
    }


    @ApiOperation(value = "/order/search", nickname = "查询订单列表", notes = "查询订单列表")
    @RequestMapping(value = "/order/search", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'order:search')")
    ReSult searchOrder(OrderDtoS dto, Pageable pageable,@SessionAttribute("user") UserDetail user) throws IOException, YjException {
        ReSult reSult= orderService.searchOrder(dto,pageable,user);
        return reSult;
    }

    @ApiOperation(value = "/order-dedail/search", nickname = "销售明细表", notes = "销售明细表")
    @RequestMapping(value = "/order-detail/search", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'order:search')")
    ReSult searchOrderDetail(OrderDetailDtoS dto, Pageable pageable, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        ReSult reSult= orderService.searchOrderDetail(dto,pageable,user);
        return reSult;
    }

}
