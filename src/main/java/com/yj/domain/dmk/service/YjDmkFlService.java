package com.yj.domain.dmk.service;

import com.yj.domain.dmk.model.YjDmkClEntity;
import com.yj.domain.dmk.model.YjDmkFlEntity;

import java.util.List;
import java.util.Map;

public interface YjDmkFlService {

    Integer insert(YjDmkFlEntity dto);

    Integer update(YjDmkFlEntity dto);

    YjDmkFlEntity get(Integer id);

    Integer delete(Integer pkid);

    List<Map<String,Object>> queryClList(String dmbz);

    Integer insertClMulti(List<YjDmkClEntity> dtos);
}
