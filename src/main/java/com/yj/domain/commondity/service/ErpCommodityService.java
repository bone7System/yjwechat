package com.yj.domain.commondity.service;


import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.commidity.ErpCommodityPojo;
import com.yj.pojo.commidity.ErpCommodityU;

import java.util.Map;

public interface ErpCommodityService {


    Integer insert(ErpCommodityPojo dto, UserDetail user) throws Exception;

    Integer update(ErpCommodityU dto, UserDetail user) throws Exception;

    Map<String, Object> get(Integer pkid);

    Integer deleteMulti(Integer[] pkids, Boolean isDelete) throws YjException;
}
