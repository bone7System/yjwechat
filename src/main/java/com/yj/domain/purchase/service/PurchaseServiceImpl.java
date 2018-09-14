package com.yj.domain.purchase.service;

import com.google.common.collect.Maps;
import com.yj.domain.common.service.NativeSqlService;
import com.yj.domain.dmk.service.YjDmkClService;
import com.yj.domain.purchase.model.Purchase;
import com.yj.domain.purchase.model.PurchaseDetail;
import com.yj.domain.purchase.model.PurchaseResult;
import com.yj.domain.purchase.repository.PurchaseDetailRepository;
import com.yj.domain.purchase.repository.PurchaseRepository;
import com.yj.domain.supplier.model.Supplier;
import com.yj.domain.supplier.repository.SupplierRepository;
import com.yj.domain.user.model.Role;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.repository.UserDetailRepository;
import com.yj.pojo.ReSult;
import com.yj.pojo.purchase.PurchaseDto;
import com.yj.pojo.purchase.PurchaseDtoS;
import com.yj.pojo.purchase.PurchaseUpdateDto;

import com.yj.pojo.purchase.vo.PurchaseVo;
import com.yj.utils.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    private NativeSqlService nativeSqlService;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private YjDmkClService yjDmkClService;
    @Override
    public ReSult addPurchase(PurchaseDto dto, UserDetail user) {

        //采购抬头
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(dto.getPurchaseDtoC(),purchase);
        purchase.setClient(user.getClient());
//        purchase.setCreateUser(user.getUserId());
//        purchase.setCreateTime(new Date());

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
       Purchase purchase= purchaseRepository.findById(dto.getPurchaseDtoU().getId()).get();
        BeanUtils.copyProperties(dto.getPurchaseDtoU(),purchase);
        purchaseRepository.save(purchase);

        dto.getItems().stream().forEach(item->{
          PurchaseDetail purchaseDetail=
                  purchaseDetailRepository.findById(item.getId()).get();
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
        StringBuffer buffer = new StringBuffer();
        buffer.append("select e.client,e.qdrq,e.createuser create_user,e.createtime create_time,e.id,e.ydlx,e.jhfs,e.ysfs,e.jsfs,e.zffs,e.lifnr,\n" +
                "                e.lifnrorder lifnr_order,e.status,e.remark,e.kbetr,d.name user_name,a1.name lifnr_name\n" +
                "                from erp_purchase e left join erp_user_detail d on e.createUser = d.id\n" +
                "\t\t\t\t\t\t\t\tLEFT JOIN erp_lfa1 a1 on e.lifnr = a1.id\n" +
                "\t\t\t\t\t\t\t\twhere 1=1 ") ;
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(dto.getId())){
            buffer.append(" and id = :id");
            map.put("e.id",dto.getId());
        }
        if(!StringUtils.isEmpty(dto.getLifnr())){
            buffer.append(" and e.lifnr = :lifnr");
            map.put("lifnr",dto.getLifnr());
        }

        if(!StringUtils.isEmpty(dto.getUserId())){
            buffer.append(" and e.createUser = :createUser");
            map.put("createUser",dto.getUserId());
        }

        if(!StringUtils.isEmpty(dto.getBeginTime())){
            if(!StringUtils.isEmpty(dto.getEndTime())){
                buffer.append(" and e.createTime BETWEEN :beginTime  and :endTime");
                map.put("beginTime",dto.getBeginTime());
                map.put("endTime",DateUtils.getEndOfDay(dto.getEndTime()));
            }else{
                buffer.append(" and e.createTime BETWEEN :beginTime  and :endTime");
                map.put("beginTime",dto.getBeginTime());
                map.put("endTime", DateUtils.getEndOfDay(dto.getBeginTime()));
            }

        }

        Page<Purchase> page= nativeSqlService.
                findBysql(buffer.toString(),pageable, PurchaseResult.class,map);



       return ReSult.success(page);
    }

    @Override
    public ReSult searchPurchase(Long id, UserDetail user) {
        //采购抬头
      //
        Purchase purchase= purchaseRepository.findById(id).get();
        PurchaseResult purchaseResult=purchaseChage(purchase);
        //采购明细

        return null;
    }

    private PurchaseResult purchaseChage(Purchase purchase){
        PurchaseResult result= new PurchaseResult();
        BeanUtils.copyProperties(purchase,result);
       Supplier supplier=
               supplierRepository.findById(result.getLifnr()).get();
       result.setLifnrName(supplier.getName());
       result.setUserName(userDetailRepository.findById(result.getCreateUser()).get().getName());

        if(!StringUtils.isEmpty(result.getJhfs())){
            result.setJhfsName(yjDmkClService.findByDm(result.getJhfs()).getMc());
        }

        if(!StringUtils.isEmpty(result.getJsfs())){
            result.setJhfsName(yjDmkClService.findByDm(result.getJsfs()).getMc());
        }
        if(!StringUtils.isEmpty(result.getYsfs())){
            result.setJhfsName(yjDmkClService.findByDm(result.getYsfs()).getMc());
        }

       return result;

    }
}
