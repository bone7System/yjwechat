package com.yj.domain.purchase.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.purchase.PurchaseDto;
import com.yj.pojo.purchase.PurchaseDtoS;
import com.yj.pojo.purchase.PurchaseUpdateDto;
import com.yj.pojo.purchase.TakeDto;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    ReSult addPurchase(PurchaseDto dto, UserDetail user);

    ReSult updatePurchase(PurchaseUpdateDto dto, UserDetail user);

    ReSult searchPurchase(PurchaseDtoS dto, Pageable pageable, UserDetail user);

    ReSult searchPurchase(Long id, UserDetail user);

    ReSult addTake(TakeDto dto, UserDetail user) throws Exception;

    void updatePurchaseStatus(Long yddh, UserDetail user);

}
