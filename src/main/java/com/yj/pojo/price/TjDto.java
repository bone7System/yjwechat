package com.yj.pojo.price;

import com.yj.domain.price.model.TjPriceHead;
import com.yj.domain.price.model.TjPriceItem;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TjDto {
    @ApiModelProperty("调价抬头")
    private TjDtoC head;
    @ApiModelProperty("调价行项目")
    private List<TjItemDtoC> items;

    public TjDtoC getHead() {
        return head;
    }

    public void setHead(TjDtoC head) {
        this.head = head;
    }

    public List<TjItemDtoC> getItems() {
        return items;
    }

    public void setItems(List<TjItemDtoC> items) {
        this.items = items;
    }
}
