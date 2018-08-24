package com.yj.domain.commondity.service;


import com.yj.common.exception.NHDBUniqueException;
import com.yj.pojo.commidity.ErpCommodityPojo;

public interface ErpCommodityService {


    Integer insert(ErpCommodityPojo dto) throws Exception;

    Integer update(ErpCommodityPojo dto) throws Exception;

    ErpCommodityPojo get(Integer pkid);

    Integer deleteMulti(Integer[] pkids);
}
