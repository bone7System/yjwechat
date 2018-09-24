package com.yj.web;

import com.yj.domain.depot.service.DepotService;
import com.yj.domain.price.service.PriceService;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.depot.DepotDtoC;
import com.yj.pojo.depot.DepotDtoU;
import com.yj.pojo.price.TjDto;
import com.yj.pojo.price.TjUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Api(value = "/depot", tags = "depot", description = "仓位接口")
public class DepotApi {
    @Autowired
    private DepotService depotService;


    @ApiOperation(value = "/depot/add", nickname = "新增仓位", notes = "新增仓位")
    @RequestMapping(value = "/depot/add", method = RequestMethod.POST, produces = {"application/json"})
    ReSult addDepot(@RequestBody DepotDtoC dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return depotService.addDepot(dto,user);
    }

    @ApiOperation(value = "/depot/update", nickname = "修改仓位", notes = "修改仓位")
    @RequestMapping(value = "/depot/update", method = RequestMethod.POST, produces = {"application/json"})
    ReSult addPrice(@RequestBody DepotDtoU dto, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return depotService.updateDepot(dto,user);
    }

    @ApiOperation(value = "/depot/serch", nickname = "仓位查询", notes = "仓位查询")
    @RequestMapping(value = "/depot/serch", method = RequestMethod.GET, produces = {"application/json"})
    ReSult searchDepot(@SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return depotService.searchDepot(user);
    }

}
