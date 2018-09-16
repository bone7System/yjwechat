package com.yj.pojo.purchase;

import com.yj.domain.purchase.model.TakeDetail;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TakeDto {
    @ApiModelProperty("抬头")
    private TakeDtoC head;
    @ApiModelProperty("行项目")
    private List<TakeDetailDtoC> items;

    public TakeDtoC getHead() {
        return head;
    }

    public void setHead(TakeDtoC head) {
        this.head = head;
    }

    public List<TakeDetailDtoC> getItems() {
        return items;
    }

    public void setItems(List<TakeDetailDtoC> items) {
        this.items = items;
    }
}
