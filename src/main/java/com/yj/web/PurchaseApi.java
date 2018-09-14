package com.yj.web;

import com.yj.domain.image.service.ImageService;
import com.yj.domain.purchase.service.PurchaseService;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.image.ImageDto;
import com.yj.pojo.purchase.PurchaseDto;
import com.yj.pojo.purchase.PurchaseDtoS;
import com.yj.pojo.purchase.PurchaseUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(value = "/purchase", tags = "purchase", description = "采购")
public class PurchaseApi {
    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value = "/purchase/add", nickname = "添加采购信息", notes = "添加采购信息")
    @RequestMapping(value = "/purchase/add", method = RequestMethod.POST, produces = {"application/json"})
    ReSult add(PurchaseDto dto,@SessionAttribute("user") UserDetail user)  {

        return purchaseService.addPurchase(dto,user);
    }

    @ApiOperation(value = "/purchase/update", nickname = "修改采购信息", notes = "修改采购信息")
    @RequestMapping(value = "/purchase/update", method = RequestMethod.POST, produces = {"application/json"})
    ReSult update(PurchaseUpdateDto dto, @SessionAttribute("user") UserDetail user)  {

        return purchaseService.updatePurchase(dto,user);
    }

    @ApiOperation(value = "/purchase/search", nickname = "查询采购信息", notes = "查询采购信息")
    @RequestMapping(value = "/purchase/search", method = RequestMethod.POST, produces = {"application/json"})
    ReSult search(PurchaseDtoS dto, Pageable pageable, @SessionAttribute("user") UserDetail user)  {

        return purchaseService.searchPurchase(dto,pageable,user);
    }


    @ApiOperation(value = "/purchase/search-byid", nickname = "查询一个采购订单", notes = "查询一个采购订单")
    @RequestMapping(value = "/purchase/search-byid", method = RequestMethod.POST, produces = {"application/json"})
    ReSult searchSingle(Long id, @SessionAttribute("user") UserDetail user)  {

        return purchaseService.searchPurchase(id,user);
    }
}
