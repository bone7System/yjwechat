package com.yj.domain.price.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.price.PriceTypeDtoC;
import com.yj.pojo.price.PriceTypeDtoU; /**
 * 价格类型
 */
public interface PriceTypeService {
    ReSult addPriceType(PriceTypeDtoC dto, UserDetail user);

    ReSult udpdatePriceType(PriceTypeDtoU dto, UserDetail user);

    ReSult searchPriceType(UserDetail user);
}
