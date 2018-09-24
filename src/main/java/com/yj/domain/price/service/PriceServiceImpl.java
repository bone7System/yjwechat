package com.yj.domain.price.service;

import com.yj.domain.common.service.NativeSqlService;
import com.yj.domain.price.model.Price;
import com.yj.domain.price.model.PriceRecord;
import com.yj.domain.price.model.TjPriceHead;
import com.yj.domain.price.model.TjPriceItem;
import com.yj.domain.price.repository.PriceRepository;
import com.yj.domain.price.repository.TjPriceHeadRepository;
import com.yj.domain.price.repository.TjPriceItemsRepository;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.price.*;

import static com.yj.utils.DateUtils.getSubtractSecond;

import com.yj.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private TjPriceHeadRepository tjPriceHeadRepository;
    @Autowired
    private TjPriceItemsRepository tjPriceItemsRepository;
    @Autowired
    private NativeSqlService nativeSqlService;
    @Override
    public ReSult addPrice(TjPriceItem dto, UserDetail user) {
        //商品编码
        String spbm = dto.getSpbm();

        List<Price> list=priceRepository.findBySpbmAndTypeAndClient(spbm,dto.getType(),user.getClient());
        if(list!=null&&list.size()>0){
              hanlerPrice(list,dto,user);
        }else{
            //没有历史价格 直接添加
            Price pp=
                    encapsulationPrice(dto,user.getClient(),dto.getBegin(),dto.getEnd());
            priceRepository.save(pp);
        }


        return null;
    }
    @Transactional
    @Override
    public ReSult addTjPrice(TjDto dto, UserDetail user) {
        //保存调价抬头
        TjPriceHead head=new TjPriceHead();
        BeanUtils.copyProperties(dto.getHead(),head);
        head.setClient(user.getClient());
        head.setCreateTime(new Date());
        head.setCreateUser(user.getId());
        tjPriceHeadRepository.save(head);


        for(TjItemDtoC item:dto.getItems()){
            TjPriceItem it=new TjPriceItem();
            BeanUtils.copyProperties(item,it);
            it.setClient(head.getClient());
            it.setEpid(head.getId());
            tjPriceItemsRepository.save(it);
            //新增价格
            addPrice(it,user);
        }


        return ReSult.success();
    }

    @Override
    public ReSult updateTjPrice(TjUpdateDto dto, UserDetail user) throws YjException {
      TjPriceHead head=
              tjPriceHeadRepository.findById(dto.getHead().getId()).get();
        if(head==null){
            throw  new YjException("调价单不存在");
        }
        if(head.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }

        BeanUtils.copyProperties(dto.getHead(),head, StringUtils.getNullPropertyNames(dto.getHead()));
        tjPriceHeadRepository.save(head);

        //删除
        if(dto.getDeleteIds()!=null && dto.getDeleteIds().size()>0){
            for(Long id:dto.getDeleteIds()){
              TjPriceItem it=  tjPriceItemsRepository.findById(id).get();
                if(it.getClient().intValue()!=user.getClient().intValue()){
                    throw  new YjException("客户端错误");
                }
                tjPriceItemsRepository.delete(it);
            }
        }

        if(dto.getItems()!=null){
            for(TjItemDtoU item:dto.getItems()) {
              TjPriceItem it=  tjPriceItemsRepository.findById(item.getId()).get();
                BeanUtils.copyProperties(item,it,StringUtils.getNullPropertyNames(item));
                tjPriceItemsRepository.save(it);
                addPrice(it,user);//调整价格

            }

        }


        return null;
    }

    @Override
    public ReSult searchPrice(String spbm, UserDetail user) {
        String sql="select e.*,c.spmc from erp_commodity_price e \n" +
                " inner join erp_commodity c on e.spbm = c.spbm\n" +
                " where e.spbm = :spbm and e.client= :client "+
                " ORDER BY `begin` desc";

        Map map=new HashMap();
        map.put("client",user.getClient());
        map.put("spbm",spbm);
       Page page =
               nativeSqlService.findBysql(sql, PageRequest.of(0, 100000),PriceRecord.class,map);
        return ReSult.success(page);
    }

    private void hanlerPrice(List<Price> list, TjPriceItem dto, UserDetail user) {
        long addBeginTime = dto.getBegin().getTime();//新增 开始时间
        long addEndTime = dto.getEnd().getTime();//新增结束时间
        for(Price price:list){
            //新增价格小于项目价格
            if(addBeginTime>price.getBegin().getTime() ){

                if(addEndTime<price.getEnd().getTime()){
                    //10.1-10.10 10.4-10.5
                    incisePrice(price,dto,user);
                }else if(addEndTime>=price.getEnd().getTime()){
                    //10.1-10.10 (10.4-10.10||10.4 10.15)-------
                    if(addBeginTime<price.getEnd().getTime()){
                        inciseEqRightPrice(price,dto,user);
                    }

                }


            }else if (addBeginTime==price.getBegin().getTime()){

                if(addEndTime<price.getEnd().getTime()){
                    //10.1-10.10 10.1-10.6
                    //切割俩个时间段
                    price.setBegin(getSubtractSecond(dto.getEnd(),1000L));
                    priceRepository.save(price);
                }else if (addEndTime>=price.getEnd().getTime()){
                    //10.1-10.10 10.1-10.15
                    priceRepository.delete(price);//删除
                }else{
                    //都相等不处理
                }
            }else {
                //addBeginTime< price.getBegin().getTime()
                if(addEndTime<price.getEnd().getTime()){
                    //10.1-10.10 (9.10-9.20--这种情况不切割||9.10-10.5||9.10-10.1)
                    if(addEndTime>price.getBegin().getTime()){
                            price.setBegin(getSubtractSecond(dto.getEnd(),1000L));
                        priceRepository.save(price);
                    }
                }else if (addEndTime>=price.getEnd().getTime()){
                    //相等 包含在区间内 删除
                    priceRepository.delete(price);
                }

            }

        }
        //保存价格
        saveCurrentPrice(dto,user);
    }

    /**
     * 新增日期开始时间在 历史区间内，结束时间相等  切割俩个时间段
     * @param price
     * @param dto
     * @param user
     */
    private void inciseEqRightPrice(Price price, TjPriceItem dto, UserDetail user) {

        //10.1-10.10 (10.4-10.10||10.4 10.15)
        price.setEnd(getSubtractSecond(dto.getBegin(),-1000L));
        priceRepository.save(price);
    }


    private void saveCurrentPrice(TjPriceItem dto, UserDetail user) {

       Price p2=
               encapsulationPrice(dto,user.getClient(),dto.getBegin(),dto.getEnd());
        priceRepository.save(p2);

    }
    /**
     * 包含在历史区间内 切割三个时间段
     * @param dto
     * @param user
     */
    private void incisePrice(Price price, TjPriceItem dto, UserDetail user) {

        Price p3=new Price();
        BeanUtils.copyProperties(price,p3);
        p3.setId(null);
        p3.setBegin(getSubtractSecond(dto.getEnd(),1000L));
        p3.setEnd(price.getEnd());
//        price
        price.setEnd(getSubtractSecond(dto.getBegin(),-1000L));
        priceRepository.save(price);




        priceRepository.save(p3);
    }


    private Price encapsulationPrice(TjPriceItem dto,Long client,Date begin,Date end){
        Price p3 = new Price();
        p3.setType(dto.getType());
        p3.setClient(client);
        p3.setSpbm(dto.getSpbm());
        p3.setBegin(begin);
        p3.setEnd(end);
        p3.setPrice(dto.getPrice());
        return p3;
    }
}
