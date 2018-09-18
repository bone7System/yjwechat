package com.yj.pojo.order;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class OrderDto {
    @ApiModelProperty("订单抬头")
    private OrderDtoC head;
    @ApiModelProperty("订单项目")
    protected List<OrderDetailDtoC> items;

    public OrderDtoC getHead() {
        return head;
    }

    public void setHead(OrderDtoC head) {
        this.head = head;
    }

    public List<OrderDetailDtoC> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailDtoC> items) {
        this.items = items;
    }
}
