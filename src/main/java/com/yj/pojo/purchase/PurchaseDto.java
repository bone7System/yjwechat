package com.yj.pojo.purchase;

import java.util.List;

/**
 * 添加采购信息
 */
public class PurchaseDto {
    private PurchaseDtoC purchaseDtoC;
    private List<PurchaseDetailDtoC> items;

    public PurchaseDtoC getPurchaseDtoC() {
        return purchaseDtoC;
    }

    public void setPurchaseDtoC(PurchaseDtoC purchaseDtoC) {
        this.purchaseDtoC = purchaseDtoC;
    }

    public List<PurchaseDetailDtoC> getItems() {
        return items;
    }

    public void setItems(List<PurchaseDetailDtoC> items) {
        this.items = items;
    }
}
