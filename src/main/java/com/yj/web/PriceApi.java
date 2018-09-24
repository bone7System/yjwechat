package com.yj.web;

import com.yj.domain.image.service.ImageService;
import com.yj.domain.price.service.PriceService;
import com.yj.domain.price.service.PriceTypeService;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.depot.DepotDtoC;
import com.yj.pojo.depot.DepotDtoU;
import com.yj.pojo.image.ImageDto;
import com.yj.pojo.price.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(value = "/price", tags = "price", description = "价格接口")
public class PriceApi {
    @Autowired
    private PriceService priceService;
    @Autowired
    private PriceTypeService priceTypeService;


    @ApiOperation(value = "/price-type/add", nickname = "新增价格类型", notes = "新增价格类型")
    @RequestMapping(value = "/price-type/add", method = RequestMethod.POST, produces = {"application/json"})
    ReSult addPriceType(@RequestBody PriceTypeDtoC dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return priceTypeService.addPriceType(dto,user);
    }

    @ApiOperation(value = "/price-type/update", nickname = "修改价格类型", notes = "价格类型")
    @RequestMapping(value = "/price-type/update", method = RequestMethod.POST, produces = {"application/json"})
    ReSult udpdatePriceType(@RequestBody PriceTypeDtoU dto, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return priceTypeService.udpdatePriceType(dto,user);
    }

    @ApiOperation(value = "/price-type/serch", nickname = "价格类型查询", notes = "价格类型查询")
    @RequestMapping(value = "/price-type/serch", method = RequestMethod.GET, produces = {"application/json"})
    ReSult searchPriceType(@SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return priceTypeService.searchPriceType(user);
    }



//    @ApiOperation(value = "/price/add", nickname = "调价", notes = "调价")
//    @RequestMapping(value = "/price/add", method = RequestMethod.POST, produces = {"application/json"})
//    ReSult addPrice(@RequestBody  PriceDto dto, @SessionAttribute("user") UserDetail user) throws IOException {
//        return priceService.addPrice(dto,user);
//    }

    @ApiOperation(value = "/price/add", nickname = "新增调价", notes = "新增调价")
    @RequestMapping(value = "/price/add", method = RequestMethod.POST, produces = {"application/json"})
    ReSult addPrice(@RequestBody TjDto dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return priceService.addTjPrice(dto,user);
    }

    @ApiOperation(value = "/price/update", nickname = "新增调价", notes = "新增调价")
    @RequestMapping(value = "/price/update", method = RequestMethod.POST, produces = {"application/json"})
    ReSult addPrice(@RequestBody TjUpdateDto dto, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return priceService.updateTjPrice(dto,user);
    }

    @ApiOperation(value = "/price/serch-record", nickname = "商品价格记录", notes = "商品价格记录")
    @RequestMapping(value = "/price/serch-record", method = RequestMethod.GET, produces = {"application/json"})
    ReSult addPrice(String spbm, @SessionAttribute("user") UserDetail user) throws IOException, YjException {
        return priceService.searchPrice(spbm,user);
    }

}
