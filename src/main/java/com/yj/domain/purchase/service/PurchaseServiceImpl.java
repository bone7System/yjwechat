package com.yj.domain.purchase.service;

import com.google.common.collect.Maps;
import com.yj.domain.common.service.NativeSqlService;
import com.yj.domain.commondity.model.ErpCommodityDetailEntity;
import com.yj.domain.commondity.model.ErpCommodityEntity;
import com.yj.domain.commondity.repository.ErpCommodityDetailRepository;
import com.yj.domain.commondity.repository.ErpCommodityRepository;
import com.yj.domain.dmk.service.YjDmkClService;
import com.yj.domain.purchase.model.*;
import com.yj.domain.purchase.repository.PurchaseDetailRepository;
import com.yj.domain.purchase.repository.PurchaseRepository;
import com.yj.domain.purchase.repository.TakeDetailRepository;
import com.yj.domain.purchase.repository.TakeRepository;
import com.yj.domain.supplier.model.Supplier;
import com.yj.domain.supplier.repository.SupplierRepository;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.repository.UserDetailRepository;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.purchase.*;

import com.yj.pojo.purchase.vo.PurchaseVo;
import com.yj.utils.DateUtils;
import static com.yj.utils.ObjectUtils.getBigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.xml.soap.Detail;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TakeRepository takeRepository;
    @Autowired
    private TakeDetailRepository takeDetailRepository;
    @Autowired
    private ErpCommodityRepository erpCommodityRepository;
    @Autowired
    private ErpCommodityDetailRepository erpCommodityDetailRepository;

    @Override
    public ReSult addPurchase(PurchaseDto dto, UserDetail user) {

        //采购抬头
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(dto.getPurchaseDtoC(),purchase);
        purchase.setClient(user.getClient());
        purchase.setCreateUser(user.getUserId());
        purchase.setCreateTime(new Date());
        purchase.setStatus(0L);

        purchaseRepository.save(purchase);
        //采购明细
        dto.getItems().stream().forEach(item->{
            PurchaseDetail detail = new PurchaseDetail();
            BeanUtils.copyProperties(item,detail);
            detail.setCgid(purchase.getId());
            detail.setClient(purchase.getClient());
            detail.setCount1(BigDecimal.ZERO);
            detail.setCount2(detail.getCount());
            detail.setStatus(0L);
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
        buffer.append("select e.type,e.client,e.qdrq,e.createuser create_user,e.createtime create_time,e.id,e.ydlx,e.jhfs,e.ysfs,e.jsfs,e.zffs,e.lifnr,\n" +
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

        Page<PurchaseResult> page= nativeSqlService.
                findBysql(buffer.toString(),pageable, PurchaseResult.class,map);
        page.getContent().stream().forEach(result->{
            if(!StringUtils.isEmpty(result.getJhfs())){
                result.setJhfsName(yjDmkClService.findByDm(result.getJhfs()).getMc());
            }

            if(!StringUtils.isEmpty(result.getJsfs())){
                result.setJsfsName(yjDmkClService.findByDm(result.getJsfs()).getMc());
            }
            if(!StringUtils.isEmpty(result.getYsfs())){
                result.setYsfsName(yjDmkClService.findByDm(result.getYsfs()).getMc());
            }

        });


       return ReSult.success(page);
    }

    @Override
    public ReSult searchPurchase(Long id, UserDetail user) {
        //采购抬头
      //
        Purchase purchase= purchaseRepository.findById(id).get();
        PurchaseResult purchaseResult=purchaseChage(purchase);
        //采购明细
        String mxsql="select d.*,c.spmc,c.sppp,c.dwdm,c.gg,c.dj\n" +
                "from erp_purchase_detail d left join erp_commodity c on d.spbm = c.spbm\n" +
                "where d.cgid="+id;
        Pageable pageable=PageRequest.of(0,10000);
        Page<PurchaseDetailResult> page= nativeSqlService.findBysql(mxsql,pageable, PurchaseDetailResult.class,null);
        PurchaseVo purchaseVo = new PurchaseVo();
        purchaseVo.setHead(purchaseResult);
        purchaseVo.setItems(page);

        return ReSult.success(purchaseVo);
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
            result.setJsfsName(yjDmkClService.findByDm(result.getJsfs()).getMc());
        }
        if(!StringUtils.isEmpty(result.getYsfs())){
            result.setYsfsName(yjDmkClService.findByDm(result.getYsfs()).getMc());
        }

       return result;

    }

    //入库
    @Transactional
    @Override
    public ReSult addTake(TakeDto dto, UserDetail user) throws Exception {
        //采购入库
        if(dto.getHead().getType().intValue()==1){
            inStore(dto,user);
        }else{
            //退货出库
            outStore(dto,user);
        }

        return ReSult.success();
    }

    private void outStore(TakeDto dto, UserDetail user) throws Exception {
        Take take = new Take();
        BeanUtils.copyProperties(dto.getHead(),take);
        take.setClient(user.getClient());
        take.setCreateTime(new Date());
        take.setCreateUser(user.getId());
        takeRepository.save(take);
        //保存 行项目
        for (TakeDetailDtoC item:dto.getItems()) {
            //消减 库存
            ErpCommodityEntity sp=
                    erpCommodityRepository.findBySpbmAndClient(item.getSpbm(),user.getClient());
            //出库的仓位 如果是采购单 应该让用户自己输入出库仓位，如果是 入库单 应该自动带出 仓位
            ErpCommodityDetailEntity ede=
                    erpCommodityDetailRepository.findBySpidAndCwAndClient(sp.getId(),item.getCw(),user.getClient());

            if(ede==null || ede.getCount().subtract(item.getCount()).intValue()<0) {
                throw new YjException(sp.getSpbm() + "库存不足");
            }
            //消减库存
            ede.setCount(ede.getCount().subtract(item.getCount()));

            erpCommodityDetailRepository.save(ede);
        }



    }

    private void inStore(TakeDto dto, UserDetail user) {
        Take take = new Take();
        BeanUtils.copyProperties(dto.getHead(),take);
        take.setClient(user.getClient());
        take.setCreateTime(new Date());
        take.setCreateUser(user.getId());
        takeRepository.save(take);

        //保存 行项目
        dto.getItems().stream().forEach(item->{
            //保存
            TakeDetail detail = new TakeDetail();
            BeanUtils.copyProperties(item,detail);
            detail.setClient(user.getClient());
            detail.setRkdh(take.getId());
            takeDetailRepository.save(detail);
            /***************************添加库存 计算移动平均价*********************************/

            //获取商品主表 得到 id
            ErpCommodityEntity sp=
                    erpCommodityRepository.findBySpbmAndClient(item.getSpbm(),user.getClient());
            //根据 商品id+仓位 获取详情表 是否存在记录   不存在添加数据  存在 修改

            //总数量
            BigDecimal sumCount=
                    erpCommodityDetailRepository.getSumCountBySpid(sp.getId());

            // 总价值
            BigDecimal sumkbetr= getBigDecimal(sumCount).multiply(getBigDecimal(sp.getKbetr()));

            ErpCommodityDetailEntity ede=
                    erpCommodityDetailRepository.findBySpidAndCwAndClient(sp.getId(),item.getCw(),user.getClient());

            if(ede==null){
                //新增
                ede = new ErpCommodityDetailEntity();
                ede.setDelFlag(1L);
                ede.setSpid(sp.getId());
                ede.setCw(item.getCw());//仓库位置
                ede.setCount(item.getCount());//商品数量
                ede.setClient(user.getClient());
                erpCommodityDetailRepository.save(ede);

            }else{
                //修改 数量
                ede.setCount(
                        ede.getCount().add(item.getCount())
                );
                erpCommodityDetailRepository.save(ede);
            }

            //获取采购行项目
            PurchaseDetail pdtail= purchaseDetailRepository.findByCgidAndRownumAndClient(
                    take.getYddh(),item.getCgrownum(),take.getClient()
            );
            //已交货 新增
            pdtail.setCount1(
                    pdtail.getCount1().add(item.getCount())
            );
            //未交货 减少
            pdtail.setCount2(
                    pdtail.getCount2().subtract(item.getCount())
            );
            if(pdtail.getCount2().intValue()==0){
                pdtail.setStatus(2L);
            }else{
                pdtail.setStatus(1L);
            }

            //计算移动平均价
            BigDecimal newKbetr= item.getCount().multiply(pdtail.getUnitprice())
                    .add(getBigDecimal(sumkbetr)).divide(item.getCount().add(getBigDecimal(sumCount)));

            sp.setKbetr(newKbetr);
            erpCommodityRepository.save(sp);
        });
    }

    /**
     * 修改采购订单 状态
     * @param yddh
     * @param user
     */
    @Override
    public void updatePurchaseStatus(Long yddh, UserDetail user) {
        //
      List<PurchaseDetail> list=
              purchaseDetailRepository.findByCgidAndClient(yddh,user.getClient());
        final Boolean[] b = {false};
      if(list!=null){
          list.stream().forEach(item->{
              if(item.getStatus().intValue()!=2){
                  b[0] =true;
                  return;
              }
          });
      }
        Purchase purchase=
                purchaseRepository.findById(yddh).get();
      if(!b[0]){
          //交货完成表示
          purchase.setStatus(2L);
      }else{
          purchase.setStatus(1L);
      }
      purchaseRepository.save(purchase);


    }


}
