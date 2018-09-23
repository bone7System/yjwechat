package com.yj.web;

import com.yj.domain.image.service.ImageService;
import com.yj.domain.price.service.PriceService;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.image.ImageDto;
import com.yj.pojo.price.PriceDto;
import com.yj.pojo.price.TjDto;
import com.yj.pojo.price.TjDtoU;
import com.yj.pojo.price.TjUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(value = "/price", tags = "price", description = "价格调整接口")
public class PriceApi {
    @Autowired
    private PriceService priceService;

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
    ReSult addPrice(@RequestBody TjUpdateDto dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return priceService.updateTjPrice(dto,user);
    }

}
