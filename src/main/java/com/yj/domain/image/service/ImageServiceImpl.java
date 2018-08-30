package com.yj.domain.image.service;

import com.yj.domain.image.model.Image;
import com.yj.domain.image.repository.ImageRepository;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.image.ImageDto;
import com.yj.pojo.ReSult;
import com.yj.utils.DateUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class ImageServiceImpl implements  ImageService {
    @Value("${image.path}")
    String imagePath;
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public ReSult uploadImage(MultipartFile file, ImageDto dto, UserDetail user) throws IOException {
        Long size=file.getSize();
        float outputQuality=0.5f;
        if(size/(1024*1024)>2){
            //大于2M 压缩
            outputQuality=0.25f;
        }
        Image image = new Image();
        BeanUtils.copyProperties(dto,image);
        image.setClient(user.getClient());
        image.setCreateTime(new Date());
        String strDate=DateUtils.formatDate(new Date());
        File f = new File(imagePath+image.getClient()+"/"+strDate);//日期
        String uuidName= UUID.randomUUID().toString().replace("-","");
        if(!f.exists()){
            f.mkdirs();
        }
        image.setPath(image.getClient()+"/"+strDate+"/"+uuidName+".jpg");

        Thumbnails.of(file.getInputStream())
                .scale(1f)
                .outputQuality(outputQuality)
                .toFile(imagePath+image.getPath());
        Image imageSave= imageRepository.save(image);

        return ReSult.success(imageSave);
    }


}
