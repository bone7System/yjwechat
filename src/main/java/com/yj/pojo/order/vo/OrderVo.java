package com.yj.pojo.order.vo;

import com.yj.domain.order.model.OrderDetailResult;
import com.yj.domain.order.model.OrderResult;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class OrderVo implements Serializable{
    @ApiModelProperty("订单抬头")
    OrderResult head;
    @ApiModelProperty("订单行项目")
    List<OrderDetailResult> items;

    public OrderResult getHead() {
        return head;
    }

    public void setHead(OrderResult head) {
        this.head = head;
    }

    public List<OrderDetailResult> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailResult> items) {
        this.items = items;
    }
}
