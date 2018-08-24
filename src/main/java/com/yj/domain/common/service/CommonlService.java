package com.yj.domain.common.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CommonlService {

    Page<Map<String,Object>> queryPage(Map<String, Object> pagination, Map<String, Object> filters,
                                       Map<String, Object> sorter, Map<String, Object> searchTextMap, Map<String, Object> searchParams,
                                       Map<String, Object> sqlParams, String sign) throws Exception;

    List<Map<String, Object>> selectCascaderList(Integer level, String sign, String cascaderValue) throws Exception;

    List<Map<String, Object>> selectDataList(String sign) throws Exception;

    List<Map<String, Object>> getTreeList(String sign, Map<String, Object> csMapm, Map<String, Object> sqlParams) throws Exception;

}
