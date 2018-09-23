package com.yj.pojo.price;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TjUpdateDto {
    @ApiModelProperty("调价抬头")
    private TjDtoU head;
    @ApiModelProperty("调价行项目")
    private List<TjItemDtoU> items;
    @ApiModelProperty("调价删除项目")
    private List<Long> deleteIds;
    public TjDtoU getHead() {
        return head;
    }

    public void setHead(TjDtoU head) {
        this.head = head;
    }

    public List<TjItemDtoU> getItems() {
        return items;
    }

    public void setItems(List<TjItemDtoU> items) {
        this.items = items;
    }

    public List<Long> getDeleteIds() {
        return deleteIds;
    }

    public void setDeleteIds(List<Long> deleteIds) {
        this.deleteIds = deleteIds;
    }
}
