package com.yj.domain.depot.service;

import com.yj.domain.depot.model.Depot;
import com.yj.domain.depot.repository.DepotRepository;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.depot.DepotDtoC;
import com.yj.pojo.depot.DepotDtoU;
import com.yj.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepotServiceImpl implements DepotService {
    @Autowired
    DepotRepository depotRepository;
    @Override
    public ReSult addDepot(DepotDtoC dto, UserDetail user) {
        Depot depot = new Depot();
        BeanUtils.copyProperties(dto,depot);
        depot.setClient(user.getClient());
        depotRepository.save(depot);
        return ReSult.success(depot);

    }

    @Override
    public ReSult updateDepot(DepotDtoU dto, UserDetail user) {
        Depot depot= depotRepository.findById(dto.getId()).get();
        BeanUtils.copyProperties(dto,depot, StringUtils.getNullPropertyNames(dto));
        depotRepository.save(depot);
        return ReSult.success(depot);

    }

    @Override
    public ReSult searchDepot(UserDetail user) {

        List list=  depotRepository.findByClientOrClient(user.getClient(),1000L);

        return ReSult.success(list);
    }
}
