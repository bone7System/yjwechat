package com.yj.domain.customer.service;

import com.google.common.collect.Lists;
import com.yj.domain.customer.model.Customer;
import com.yj.domain.customer.model.LinkmanCustomer;
import com.yj.domain.customer.repository.CustomerRepository;
import com.yj.domain.customer.repository.LinkmanCustomerRepository;
import com.yj.domain.supplier.model.Supplier;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.customer.*;
import com.yj.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class CustomerServiceImpl implements  CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LinkmanCustomerRepository linkmanCustomerRepository;
    @Override
    public ReSult addCustomer(CustomerDtoC dto, UserDetail user) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto,customer);
        customer.setClient(user.getClient());
        customer.setCreateTime(new Date());
        customer.setCreateUserid(user.getId());
        customerRepository.save(customer);
        return ReSult.success();

    }

    @Override
    public ReSult updateCustomer(CustomerDtoU dto, UserDetail user) throws YjException {
       Customer customer= customerRepository.findById(dto.getId()).get();
       if(customer==null){
           throw new YjException("客户未找到");
       }
       if(customer.getClient().intValue()!=user.getClient().intValue()){
           throw  new YjException("客户端错误");
       }
       BeanUtils.copyProperties(dto,customer);
       customerRepository.save(customer);
       return ReSult.success();

    }

    @Override
    public ReSult searchCustomer(CustomerDtoS dto, UserDetail user, Pageable pageable) {

        Page<Customer> pages= customerRepository.findAll(new Specification<Customer>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                //client 是必须的
                predicates.add(cb.equal(root.get("client"),user.getClient()));
                predicates.add(cb.gt(root.get("flag"),-1));
                if(!StringUtils.isEmpty(dto.getName())){
                    predicates.add(cb.like(root.get("name"),"%"+dto.getName()+"%"));

                }
                if(!StringUtils.isEmpty(dto.getAddress())){
                    predicates.add(cb.like(root.get("address"),"%"+dto.getAddress()+"%"));

                }

                return query.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();

            }
        },pageable);



        return ReSult.success(pages);
    }

    @Override
    public ReSult deleteCustomer(Long id, UserDetail user) throws YjException {
        Customer customer=customerRepository.findById(id).get();
        if(customer==null){
            throw  new YjException("客户编号错误");
        }
        if(customer.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        customer.setFlag(-1L);
        customerRepository.save(customer);
        return ReSult.success();
    }

    @Override
    public ReSult addCustomerLinkMan(CustomerLinkManDtoC dto, UserDetail user) {
        LinkmanCustomer customer = new LinkmanCustomer();
        BeanUtils.copyProperties(dto,customer);
        customer.setCreateTime(new Date());
        customer.setClient(user.getClient());
        customer.setCreateUserid(user.getId());
        linkmanCustomerRepository.save(customer);

        return ReSult.success();
    }

    @Override
    public ReSult updateCustomerLinkMan(CustomerLinkManDtoU dto, UserDetail user) throws YjException {

      LinkmanCustomer customer=
              linkmanCustomerRepository.findById(dto.getId()).get();
        if(customer==null){
            throw  new YjException("联系人编号错误");
        }
        if(customer.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        BeanUtils.copyProperties(dto,customer,StringUtils.getNullPropertyNames(dto));
        linkmanCustomerRepository.save(customer);
        return ReSult.success();
    }

    @Override
    public ReSult searchCustomerLinkMan(Long khbh, UserDetail user) {
       List list= linkmanCustomerRepository.findByKaidAndClientOrderByCreateTimeAsc(khbh,user.getClient());

        return ReSult.success(list);
    }

    @Override
    public ReSult deleteLinkManCustomer(Long id, UserDetail user) throws YjException {
       LinkmanCustomer customer= linkmanCustomerRepository.findById(id).get();
        if(customer==null){
            throw  new YjException("联系人编号错误");
        }
        if(customer.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        linkmanCustomerRepository.delete(customer);
        return ReSult.success();
    }


}
