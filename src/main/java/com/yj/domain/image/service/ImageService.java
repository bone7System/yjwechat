package com.yj.domain.image.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.image.ImageDto;
import com.yj.pojo.ReSult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ReSult uploadImage(MultipartFile file, ImageDto dto, UserDetail user) throws IOException;
}
