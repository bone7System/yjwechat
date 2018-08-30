package com.yj.web;

import com.yj.domain.image.service.ImageService;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.customer.CustomerDto;
import com.yj.pojo.ReSult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Api(value = "/customer", tags = "customer", description = "客户")
public class CustomerApi {
    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "/customer/add", nickname = "添加客户", notes = "添加客户")
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST, produces = {"application/json"})
    ReSult test(CustomerDto dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return null;
    }
}
