package com.yj.domain.price.service;

import com.yj.domain.common.service.NativeSqlService;
import com.yj.domain.price.model.*;
import com.yj.domain.price.repository.PriceRepository;
import com.yj.domain.price.repository.PriceTypeRepository;
import com.yj.domain.price.repository.TjPriceHeadRepository;
import com.yj.domain.price.repository.TjPriceItemsRepository;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.price.*;
import com.yj.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yj.utils.DateUtils.getSubtractSecond;

@Service
public class PriceTypeServiceImpl implements PriceTypeService {
    @Autowired
    private PriceTypeRepository priceTypeRepository;


    @Override
    public ReSult addPriceType(PriceTypeDtoC dto, UserDetail user) {

        PriceType depot = new PriceType();
        BeanUtils.copyProperties(dto,depot);
        depot.setClient(user.getClient());
        priceTypeRepository.save(depot);
        return ReSult.success(depot);
    }

    @Override
    public ReSult udpdatePriceType(PriceTypeDtoU dto, UserDetail user) {
        PriceType depot= priceTypeRepository.findById(dto.getId()).get();
        BeanUtils.copyProperties(dto,depot, StringUtils.getNullPropertyNames(dto));
        priceTypeRepository.save(depot);
        return ReSult.success(depot);
    }

    @Override
    public ReSult searchPriceType(UserDetail user) {
        List list=  priceTypeRepository.findByClientOrClient(user.getClient(),1000L);

        return ReSult.success(list);
    }



}
