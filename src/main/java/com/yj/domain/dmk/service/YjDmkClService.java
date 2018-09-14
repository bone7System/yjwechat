package com.yj.domain.dmk.service;

import com.yj.domain.dmk.model.YjDmkClEntity;

import java.util.List;
import java.util.Map;

public interface YjDmkClService {

    Integer deleteMulti(Integer[] pkids);

    Integer deleteByDmbzAndDm(YjDmkClEntity dto);

    List<Map<String,Object>> getFdmListByFflid(String dm);

    Integer operateStatus(Integer[] pkids, String zt);

    YjDmkClEntity findByDm(String dm);
}
