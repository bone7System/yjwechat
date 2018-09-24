package com.yj.domain.price.service;

import com.yj.domain.price.model.TjPriceItem;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.depot.DepotDtoC;
import com.yj.pojo.price.TjDto;
import com.yj.pojo.price.TjUpdateDto;

public interface PriceService {
    ReSult addPrice(TjPriceItem dto, UserDetail user);

    ReSult addTjPrice(TjDto dto, UserDetail user);

    ReSult updateTjPrice(TjUpdateDto dto, UserDetail user) throws YjException;

    ReSult searchPrice(String spbm, UserDetail user);


}
