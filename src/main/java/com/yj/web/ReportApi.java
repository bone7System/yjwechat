package com.yj.web;

import com.google.common.collect.Lists;
import com.yj.config.JsonDateValueProcessor;
import com.yj.domain.report.service.ReportService;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.report.KcDto;
import com.yj.pojo.supplier.SupplierDtoC;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportApi {
    @Autowired
    private ReportService reportService;

//    @RequestMapping("abc")
//    public String test(){
//        List list= Lists.newArrayList();
//        Map map=new HashMap<>();
//        list.add(map);
//        map.put("a",new Date());
//
//        JsonConfig config=new JsonConfig();
//        config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
//        return JSONArray.fromObject(list,config).toString();
//    }



    @ApiOperation(value = "/report/search-store", nickname = "库存明细表", notes = "库存明细表")
    @RequestMapping(value = "/report/search-store", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('', 'report:search-store')")
    ReSult searchStore(KcDto kcDto, Pageable pageable, @SessionAttribute("user") UserDetail user) {
        return reportService.searchStore(kcDto,pageable,user);
    }

}
