package com.yj.domain.purchase.service;

import com.yj.domain.common.service.NativeSqlService;
import com.yj.domain.purchase.model.Purchase;
import com.yj.domain.purchase.model.PurchaseDetail;
import com.yj.domain.purchase.repository.PurchaseDetailRepository;
import com.yj.domain.purchase.repository.PurchaseRepository;
import com.yj.domain.user.model.Role;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;
import com.yj.pojo.purchase.PurchaseDto;
import com.yj.pojo.purchase.PurchaseDtoS;
import com.yj.pojo.purchase.PurchaseUpdateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    private NativeSqlService nativeSqlService;

    @Override
    public ReSult addPurchase(PurchaseDto dto, UserDetail user) {

        //采购抬头
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(dto.getPurchaseDtoC(),purchase);
        purchase.setClient(user.getClient());
        purchase.setCreateUser(user.getUserId());
        purchase.setCreateTime(new Date());

        purchaseRepository.save(purchase);
        //采购明细
        dto.getItems().stream().forEach(item->{
            PurchaseDetail detail = new PurchaseDetail();
            BeanUtils.copyProperties(item,detail);
            detail.setCgid(purchase.getId());
            detail.setClient(purchase.getClient());
            purchaseDetailRepository.save(detail);

        });


        return ReSult.success(purchase);
    }

    @Override
    public ReSult updatePurchase(PurchaseUpdateDto dto, UserDetail user) {
       Purchase purchase= purchaseRepository.getOne(dto.getPurchaseDtoU().getId());
        BeanUtils.copyProperties(dto.getPurchaseDtoU(),purchase);
        purchaseRepository.save(purchase);

        dto.getItems().stream().forEach(item->{
          PurchaseDetail purchaseDetail=
                  purchaseDetailRepository.getOne(item.getId());
          BeanUtils.copyProperties(item,purchaseDetail);

          purchaseDetailRepository.save(purchaseDetail);

        });

        if(dto.getDeleteIds()!=null ){
            dto.getDeleteIds().stream().forEach(id->{
                purchaseDetailRepository.deleteById(id);
            });
        }


        return ReSult.success();
    }

    @Override
    public ReSult searchPurchase(PurchaseDtoS dto, Pageable pageable, UserDetail user) {
        String sql = "select * from erp_role ";
       Page page= nativeSqlService.findBysql(sql,pageable, Role.class,null);

       return ReSult.success(page);
    }
}
