package com.yj.pojo.purchase.vo;

import com.yj.domain.purchase.model.PurchaseDetailResult;
import com.yj.domain.purchase.model.PurchaseResult;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

public class PurchaseVo {

    private PurchaseResult head;
    private Page<PurchaseDetailResult> items;

    public PurchaseResult getHead() {
        return head;
    }

    public void setHead(PurchaseResult head) {
        this.head = head;
    }

    public Page<PurchaseDetailResult> getItems() {
        return items;
    }

    public void setItems(Page<PurchaseDetailResult> items) {
        this.items = items;
    }
}
