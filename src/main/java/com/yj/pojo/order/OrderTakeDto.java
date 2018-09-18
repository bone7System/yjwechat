package com.yj.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yj.domain.order.model.OrderTake;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


public class OrderTakeDto {

    @ApiModelProperty("订单交货抬头")
    private OrderTakeDtoC head;
    @ApiModelProperty("订单交货项目")
    protected List<OrderTakeDetailDtoC> items;

    public OrderTakeDtoC getHead() {
        return head;
    }

    public void setHead(OrderTakeDtoC head) {
        this.head = head;
    }

    public List<OrderTakeDetailDtoC> getItems() {
        return items;
    }

    public void setItems(List<OrderTakeDetailDtoC> items) {
        this.items = items;
    }
}
