package com.yj.web;

import com.yj.domain.image.service.ImageService;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.image.ImageDto;
import com.yj.pojo.ReSult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(value = "/image", tags = "image", description = "图片服务接口")
public class ImageApi {
    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "/image/upload", nickname = "图片上传服务", notes = "图片上传服务")
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST, produces = {"application/json"})
    ReSult test(@RequestParam("file") MultipartFile file, ImageDto dto, @SessionAttribute("user") UserDetail user) throws IOException {
        return imageService.uploadImage(file,dto,user);
    }
}
