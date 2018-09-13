package com.yj.pojo.purchase;

import java.util.List;

/**
 * 添加采购信息
 */
public class PurchaseUpdateDto {
    private PurchaseDtoU purchaseDtoU;
    private List<PurchaseDetailDtoU> items;
    private List<Long> deleteIds;

    public List<Long> getDeleteIds() {
        return deleteIds;
    }

    public void setDeleteIds(List<Long> deleteIds) {
        this.deleteIds = deleteIds;
    }

    public PurchaseDtoU getPurchaseDtoU() {
        return purchaseDtoU;
    }

    public void setPurchaseDtoU(PurchaseDtoU purchaseDtoU) {
        this.purchaseDtoU = purchaseDtoU;
    }

    public List<PurchaseDetailDtoU> getItems() {
        return items;
    }

    public void setItems(List<PurchaseDetailDtoU> items) {
        this.items = items;
    }
}
