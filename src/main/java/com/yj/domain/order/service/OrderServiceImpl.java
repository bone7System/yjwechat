package com.yj.domain.order.service;

import com.yj.domain.commondity.model.ErpCommodityDetailEntity;
import com.yj.domain.commondity.model.ErpCommodityEntity;
import com.yj.domain.commondity.repository.ErpCommodityDetailRepository;
import com.yj.domain.commondity.repository.ErpCommodityRepository;
import com.yj.domain.order.model.Order;
import com.yj.domain.order.model.OrderDetail;
import com.yj.domain.order.model.OrderTake;
import com.yj.domain.order.model.OrderTakeDetail;
import com.yj.domain.order.repository.OrderDetailRepository;
import com.yj.domain.order.repository.OrderRepository;
import com.yj.domain.order.repository.OrderTakeDetailRepository;
import com.yj.domain.order.repository.OrderTakeRepository;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.order.*;

import com.yj.utils.ObjectUtils;
import com.yj.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements  OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderTakeRepository orderTakeRepository;
    @Autowired
    private OrderTakeDetailRepository orderTakeDetailRepository;
    @Autowired
    private ErpCommodityDetailRepository erpCommodityDetailRepository;
    @Autowired
    private ErpCommodityRepository erpCommodityRepository;

    @Transactional
    @Override
    public ReSult addOrder(OrderDto dto, UserDetail user) {

        //保存订单
        Order  order = new Order();
        BeanUtils.copyProperties(dto.getHead(),order);
        order.setClient(user.getClient());
        order.setCreateTime(new Date());
        order.setCreateUser(user.getId());
        order.setStatus(0L);//初始状态
        if(order.getType().intValue()==4L){
            //定金单是不需要 交货
            order.setStatus(2L);
        }
        orderRepository.save(order);
        saveOrderItem(order,dto.getItems());

        //保存行项目
//        switch (dto.getHead().getType()+""){
//            case "4":
//                //定金单 另行处理
//                break;
//             default:
//                //  其他类型单据
//                 saveOrderItem(order,dto.getItems());
//                break;
//        }


        return ReSult.success();
    }


    private void saveOrderItem(Order order, List<OrderDetailDtoC> items) {
        items.stream().forEach(item->{
            OrderDetail detail=new OrderDetail();
            BeanUtils.copyProperties(item,detail);
            detail.setOrderId(order.getId());
            detail.setClient(order.getClient());
            detail.setCount2(detail.getCount());
            orderDetailRepository.save(detail);

        });
    }

    @Transactional
    @Override
    public ReSult updateOrder(OrderUpdateDtoU dto, UserDetail user) throws YjException {
       Order order= orderRepository.findById(dto.getHead().getId()).get();
       //完成状态 不能修改(定金特殊)
       if(order.getStatus().intValue()==2&& order.getType().intValue()!=4){
           throw  new YjException("订单已完结,不允许修改");
       }
       BeanUtils.copyProperties(dto.getHead(),order, StringUtils.getNullPropertyNames(dto.getHead()));
       orderRepository.save(order);

       //保存 行项目
        for(OrderDetailDtoU item:dto.getItems()) {
          OrderDetail detail=  orderDetailRepository.findById(item.getId()).get();
          BeanUtils.copyProperties(item,detail, StringUtils.getNullPropertyNames(item));
          //修改的数量一定要大于等于0
          if(detail.getCount().subtract(detail.getCount1()).intValue()<0){
              throw  new YjException(detail.getSpbm()+"不能小于已交货的量");
          }
          //重新设置 未交货数量
          detail.setCount2(detail.getCount().subtract(detail.getCount1()));

          orderDetailRepository.save(detail);
        };
        return ReSult.success();
    }

    @Override
    public ReSult deleteOrder(Long id, UserDetail user) throws YjException {
       Order order= orderRepository.findById(id).get();
       if(order==null){
           throw  new YjException("订单不存在");
       }
       if(order.getStatus().intValue()==2){
            throw  new YjException("订单已完结，不允许删除");
       }
       if(order.getClient().intValue()!=user.getClient().intValue()){
           throw  new YjException("客户端不存在");
       }
      List<OrderDetail> list= orderDetailRepository.findByOrderIdAndClient(id,user.getClient());
       if(list!=null && list.size()>0){
           //存在交货记录不应该物理删除
           order.setDelFlag(-1L);
           orderRepository.save(order);
           return ReSult.success();
       }

        orderRepository.delete(order);
        list.stream().forEach(item->{
            orderDetailRepository.delete(item);
        });
        return ReSult.success();
    }

    /**
     * 订单交货
     * @param dto
     * @param user
     * @return
     */
    @Transactional
    @Override
    public ReSult addDelivery(OrderTakeDto dto, UserDetail user) throws YjException {

        Order order= orderRepository.findById(dto.getHead().getLydh()).get();
        if(order==null){
            throw  new YjException("订单不存在");
        }
        if(order.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        OrderTake take=new OrderTake();
        BeanUtils.copyProperties(dto.getHead(),take);
        take.setLylx("xs");
        take.setClient(user.getClient());
        take.setCreateTime(new Date());
        take.setCreateUser(user.getId());
        orderTakeRepository.save(take);

        for(OrderTakeDetailDtoC item:dto.getItems()){
            ErpCommodityEntity sp=
                    erpCommodityRepository.findBySpbmAndClient(item.getSpbm(),user.getClient());
            ErpCommodityDetailEntity ede=
                    erpCommodityDetailRepository.findBySpidAndCwAndClient(sp.getId(),item.getCw(),user.getClient());
            //出库 减少库存
            if(item.getType().intValue()==1){

               if(ede==null || ede.getCount().subtract(item.getCount()).intValue() <0 ){
                    throw new YjException("商品库存不足");
               }
               ede.setCount(ede.getCount().subtract(item.getCount()));
               erpCommodityDetailRepository.save(ede);
            }else{
                //一般退货  添加库存
                if(ede==null){
                    ede = new ErpCommodityDetailEntity();
                    ede.setDelFlag(1L);
                    ede.setSpid(sp.getId());
                    ede.setCw(item.getCw());//仓库位置
                    ede.setCount(item.getCount());//商品数量
                    ede.setClient(user.getClient());

                }else{
                    ede.setCount(
                            ede.getCount().add(item.getCount())
                    );
                }
                erpCommodityDetailRepository.save(ede);

            }
            OrderTakeDetail detail = new OrderTakeDetail();
            BeanUtils.copyProperties(item,detail);
            detail.setClient(take.getClient());
            detail.setTkid(take.getId());
            orderTakeDetailRepository.save(detail);

            //修改订单状态数据
            saveOrderItemStatus(take,detail);

        }


        return ReSult.success();
    }

    /**
     * 判断订单是否全部交货完成
     * @param lydh
     * @param client
     */
    @Override
    public void updateOrderStatus(Long lydh, Long client) throws YjException {
        Order order= orderRepository.findById(lydh).get();
        if(order==null){
            throw  new YjException("订单不存在");
        }
        if(order.getClient().intValue()!=client.intValue()){
            throw  new YjException("客户端错误");
        }
        List<OrderDetail> list=orderDetailRepository.findByOrderIdAndClientAndStatusLessThan(lydh,client,2L);
        if(list==null||list.size()<=0){
            order.setStatus(2L);
            orderRepository.save(order);
        }

    }

    /**
     * 冲销交货单
     * @param jhid
     * @param user
     * @return
     */
    @Override
    public ReSult addReDelivery(Long jhid, UserDetail user) throws YjException {
      OrderTake orderTake= orderTakeRepository.findById(jhid).get();
        if(orderTake==null){
            throw  new YjException("交货单不存在");
        }
        if(orderTake.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        if(orderTake.getCxdh()!=null){
            throw  new YjException("单号已被冲销,请检查交货单");
        }

        OrderTake reOrderTake = new OrderTake();
        BeanUtils.copyProperties(orderTake,reOrderTake);
        reOrderTake.setId(null);
        reOrderTake.setLylx("jh");
        reOrderTake.setLydh(orderTake.getId());
        reOrderTake.setCreateTime(new Date());
        reOrderTake.setCreateUser(user.getId());
        orderTakeRepository.save(reOrderTake);

       List<OrderTakeDetail> items=
         orderTakeDetailRepository.findByTkidAndClient(orderTake.getId(),user.getClient());

       Boolean orderStatus=false;
        for (OrderTakeDetail item:items) {
            ErpCommodityEntity sp=
                    erpCommodityRepository.findBySpbmAndClient(item.getSpbm(),user.getClient());
            ErpCommodityDetailEntity ede=
                    erpCommodityDetailRepository.findBySpidAndCwAndClient(sp.getId(),item.getCw(),user.getClient());
            // 出库 冲销 为 入库
            if(item.getType().intValue()==1){
                //添加库存
                if(ede==null){
                    ede = new ErpCommodityDetailEntity();
                    ede.setDelFlag(1L);
                    ede.setSpid(sp.getId());
                    ede.setCw(item.getCw());//仓库位置
                    ede.setCount(item.getCount());//商品数量
                    ede.setClient(user.getClient());

                }else{
                    ede.setCount(
                            ede.getCount().add(item.getCount())
                    );
                }
                erpCommodityDetailRepository.save(ede);


            }else{
                if(ede==null || ede.getCount().subtract(item.getCount()).intValue()<0) {
                    throw new YjException(sp.getSpbm() + "库存不足");
                }
                //消减库存
                ede.setCount(ede.getCount().subtract(item.getCount()));

                erpCommodityDetailRepository.save(ede);

            }
            //修改订单状态
          OrderDetail detail= saveOrderItemStatusRe(reOrderTake,item);
          if(detail.getStatus().longValue()==1){
              orderStatus = true;
          }
        }
        Order order=  orderRepository.findById(orderTake.getLydh()).get();
        order.setStatus(
                orderStatus?1L:0L
        );

        orderRepository.save(order);

        return ReSult.success();
    }

    @Override
    public ReSult searchOrder(OrderDtoS dto, UserDetail user) {
        return null;
    }

    /**
     * 冲销 修改订单状态
     * @param take
     * @param detail
     * @throws YjException
     */
    private OrderDetail saveOrderItemStatusRe(OrderTake take, OrderTakeDetail detail) throws YjException {
        OrderDetail dd= orderDetailRepository
                .findByOrderIdAndRownumAndClient(take.getLydh(),detail.getOrderRowNum(),detail.getClient());
        //已交货数量 减除
        BigDecimal yjh=dd.getCount1().subtract(detail.getCount());
        if(yjh.intValue()<0){
            throw  new YjException("交货数量异常");
        }
        dd.setCount1(yjh);
        dd.setCount2(
                dd.getCount2().add(detail.getCount())
        );

        if(yjh.intValue()==0){
            dd.setStatus(0L);
        }else{
            dd.setStatus(1L);
        }

        orderDetailRepository.save(dd);

        return dd;
    }


    private void saveOrderItemStatus(OrderTake take, OrderTakeDetail detail) throws YjException {
       OrderDetail dd= orderDetailRepository
                .findByOrderIdAndRownumAndClient(take.getLydh(),detail.getOrderRowNum(),detail.getClient());

        BigDecimal yjh=dd.getCount1().add(detail.getCount());
        dd.setStatus(1L);//行项目部分交货

        //如果交货数量 过大  应该不能保存
        if(yjh.intValue()>dd.getCount().intValue()){
            throw new YjException(detail.getSpbm()+"交货数量大于订单数量");
        }else if(yjh.intValue()==dd.getCount().intValue()){
            dd.setStatus(2L);//行项目 全部交货完成
        }

       dd.setCount1(yjh );

       dd.setCount2( dd.getCount2().subtract(detail.getCount()));

       orderDetailRepository.save(dd);

    }

}
