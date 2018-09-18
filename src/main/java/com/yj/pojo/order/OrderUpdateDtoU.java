package com.yj.pojo.order;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class OrderUpdateDtoU {
    @ApiModelProperty("订单抬头")
    private OrderDtoU head;
    @ApiModelProperty("订单姓名")
    protected List<OrderDetailDtoU> items;

    public OrderDtoU getHead() {
        return head;
    }

    public void setHead(OrderDtoU head) {
        this.head = head;
    }

    public List<OrderDetailDtoU> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailDtoU> items) {
        this.items = items;
    }
}
