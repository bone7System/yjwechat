package com.yj.domain.purchase.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.purchase.PurchaseDto;
import com.yj.pojo.purchase.PurchaseDtoS;
import com.yj.pojo.purchase.PurchaseUpdateDto;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    ReSult addPurchase(PurchaseDto dto, UserDetail user);

    ReSult updatePurchase(PurchaseUpdateDto dto, UserDetail user);

    ReSult searchPurchase(PurchaseDtoS dto, Pageable pageable, UserDetail user);
}
