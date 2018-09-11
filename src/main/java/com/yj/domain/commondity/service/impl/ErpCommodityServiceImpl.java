package com.yj.domain.commondity.service.impl;


import com.yj.domain.commondity.model.ErpCommodityDetailEntity;
import com.yj.domain.commondity.model.ErpCommodityEntity;
import com.yj.domain.commondity.repository.ErpCommodityDetailRepository;
import com.yj.domain.commondity.repository.ErpCommodityPriceRepository;
import com.yj.domain.commondity.repository.ErpCommodityRepository;
import com.yj.domain.commondity.service.ErpCommodityService;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.commidity.ErpCommodityPojo;
import com.yj.pojo.commidity.ErpCommodityU;
import com.yj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ErpCommodityServiceImpl implements ErpCommodityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ErpCommodityRepository erpCommodityRepository;
    @Autowired
    private ErpCommodityDetailRepository erpCommodityDetailRepository;
    @Autowired
    private ErpCommodityPriceRepository erpCommodityPriceRepository;

    @Override
    public Integer insert(ErpCommodityPojo dto, UserDetail user) throws Exception {

            ErpCommodityEntity cPO = new ErpCommodityEntity();
            BeanUtils.copyProperties(dto,cPO);

            cPO.setDelFlag(0);
            erpCommodityRepository.save(cPO);

            return 1;
    }

    @Override
    public Integer update(ErpCommodityU dto, UserDetail user) throws Exception {

        if(dto.getSpid()==null){
            throw  new YjException("商品未找到");
        }

        ErpCommodityEntity entity=
                erpCommodityRepository.findById(dto.getSpid()).get();
        if(entity.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        //商品存在数量的情况下，不允许修改单位
        if(StringUtils.isEmpty(dto.getDwdm())){
            List<ErpCommodityDetailEntity> listDetail=
                    erpCommodityDetailRepository.findBySpidAndCountGgGreaterThan(dto.getSpid(),0F);

            if(listDetail!=null&&listDetail.size()>0){
                //商品存在数量 不允许 修改
                throw  new YjException("商品单位不允许修改");

            }
        }
        BeanUtils.copyProperties(dto,entity,StringUtils.getNullPropertyNames(dto));
        entity.setDwdm(dto.getDwdm());


        erpCommodityRepository.save(entity);



//        try {
//            ErpCommodityEntity cPO = new ErpCommodityEntity();
//            cPO.setId(dto.getSpid());
//            cPO.setSpbm(dto.getSpbm());
//            cPO.setSpmc(dto.getSpmc());
//            cPO.setSpms(dto.getSpms());
//            cPO.setSplxdm(dto.getSplxdm());
//            cPO.setSppp(dto.getSppp());
//
//            cPO.setDelFlag(0);
//            erpCommodityRepository.save(cPO);
//            ErpCommodityDetailEntity cDetailPO = new ErpCommodityDetailEntity();
//
//            cDetailPO.setGg(dto.getGg());
//            cDetailPO.setDj(dto.getDj());
//            cDetailPO.setDwdm(dto.getDwdm());
//            cDetailPO.setSpid(dto.getSpid());
//            cDetailPO.setDelFlag(0);
//            erpCommodityDetailRepository.save(cDetailPO);
//
//            return 1;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            NHExpHandleUtils.throwesException(e);
//        }
        return 1;
    }

    @Override
    public Map<String, Object> get(Integer pkid) {
        Map<String, Object> map = erpCommodityRepository.findBySpid(pkid);
        return map;
    }

    /**
     * 删除操作，其实是修改删除状态未1，表示已经删除
     * @param pkids
     * @param isDelete
     * @return
     */
    @Override
    public Integer deleteMulti(Integer[] pkids, Boolean isDelete) throws YjException {


        for (Integer pkid : pkids) {
            ErpCommodityEntity entity=   erpCommodityRepository.findById(pkid).get();
            //商品删除 查看明细表是否有数量
            if(!isDelete){
               List list=
                       erpCommodityDetailRepository.findBySpidAndCountGgGreaterThan(pkid,0F);
               if(list!=null&&list.size()>0){

                   throw new YjException("商品删除失败："+entity.getSpms());
               }


            }
            entity.setDelFlag(-1);
            erpCommodityRepository.save(entity);
        }
       // erpCommodityPriceRepository.updateDelFlag(1,pkids);
       // erpCommodityDetailRepository.updateDelFlag(1,pkids);
       // erpCommodityRepository.updateDelFlag(-1,pkids);
        return 1;
    }
}
