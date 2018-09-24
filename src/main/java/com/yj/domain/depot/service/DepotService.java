package com.yj.domain.depot.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.depot.DepotDtoC;
import com.yj.pojo.depot.DepotDtoU;
import com.yj.pojo.image.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DepotService {

    ReSult addDepot(DepotDtoC dto, UserDetail user);

    ReSult updateDepot(DepotDtoU dto, UserDetail user);

    ReSult searchDepot(UserDetail user);

}
