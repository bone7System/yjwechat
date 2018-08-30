package com.yj.domain.commondity.service;


import com.yj.common.exception.NHDBUniqueException;
import com.yj.pojo.commidity.ErpCommodityPojo;

import java.util.Map;

public interface ErpCommodityService {


    Integer insert(ErpCommodityPojo dto) throws Exception;

    Integer update(ErpCommodityPojo dto) throws Exception;

    Map<String, Object> get(Integer pkid);

    Integer deleteMulti(Integer[] pkids);
}
