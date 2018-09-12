package com.yj.domain.supplier.service;

import com.google.common.collect.Lists;
import com.yj.domain.customer.model.LinkmanCustomer;
import com.yj.domain.supplier.model.LinkmanSupplier;
import com.yj.domain.supplier.model.Supplier;
import com.yj.domain.supplier.repository.LinkmanSupplierRepository;
import com.yj.domain.supplier.repository.SupplierRepository;
import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.supplier.*;
import com.yj.utils.StringUtils;
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

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private LinkmanSupplierRepository linkmanSupplierRepository;
    @Override
    public ReSult addSupplier(SupplierDtoC supplierDtoC, UserDetail user) {
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(supplierDtoC,supplier);
        supplier.setClient(user.getClient());
        supplier.setCreateUserid(user.getId());//创建人
        supplier.setCreateTime(new Date());
        supplierRepository.save(supplier);

        return ReSult.success();
    }

    @Override
    public ReSult updateSupplier(SupplierDtoU supplierDtoU, UserDetail user) throws YjException {
       Supplier supplier= supplierRepository.findById(supplierDtoU.getId()).get();
        if(supplier==null){
            throw  new YjException("供应商编号错误");
        }
        if(supplier.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        BeanUtils.copyProperties(supplierDtoU,supplier);

        supplierRepository.save(supplier);

        return ReSult.success();

    }

    @Override
    public ReSult searchSupplier(final SupplierDtoS dto, UserDetail user, Pageable pageable) {

       Page<Supplier> pages= supplierRepository.findAll(new Specification<Supplier>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                if(!StringUtils.isEmpty(dto.getCity())){
                    predicates.add(cb.like(root.get("city"),"%"+dto.getCity()+"%"));

                }
                if(!StringUtils.isEmpty(dto.getJlfw())){
                    predicates.add(cb.like(root.get("jlfw"),"%"+dto.getJlfw()+"%"));

                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();

            }
        },pageable);

        return ReSult.success(pages);
    }

    @Override
    public ReSult deleteSupplier(Long id, UserDetail user) throws YjException {
        Supplier supplier=supplierRepository.findById(id).get();
        if(supplier==null){
            throw  new YjException("供应商编号错误");
        }
        if(supplier.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }

        supplier.setFlag(-1L);
        supplierRepository.save(supplier);
        return ReSult.success();
    }

    @Override
    public ReSult addSpplierSpplier(SupplierLinkManDtoC dto, UserDetail user) {

        LinkmanSupplier lifnr = new LinkmanSupplier();
        BeanUtils.copyProperties(dto,lifnr);
        lifnr.setCreateTime(new Date());
        lifnr.setClient(user.getClient());
        lifnr.setCreateUserid(user.getId());
        linkmanSupplierRepository.save(lifnr);

        return ReSult.success();
    }

    @Override
    public ReSult updateCustomerSpplier(SupplierLinkManDtoU dto, UserDetail user) throws YjException {
        LinkmanSupplier lifnr=
                linkmanSupplierRepository.getOne(dto.getId());
        if(lifnr==null){
            throw  new YjException("联系人编号错误");
        }
        if(lifnr.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        BeanUtils.copyProperties(dto,lifnr,StringUtils.getNullPropertyNames(dto));
        linkmanSupplierRepository.save(lifnr);
        return ReSult.success();
    }

    @Override
    public ReSult searchCustomerSpplier(Long khbh, UserDetail user) {
        List list= linkmanSupplierRepository.findByLfidAndClientOrderByCreateTimeAsc(khbh,user.getClient());

        return ReSult.success(list);
    }

    @Override
    public ReSult deleteLinkManSpplier(Long id, UserDetail user) throws YjException {

        LinkmanSupplier customer= linkmanSupplierRepository.getOne(id);
        if(customer==null){
            throw  new YjException("联系人编号错误");
        }
        if(customer.getClient().intValue()!=user.getClient().intValue()){
            throw  new YjException("客户端错误");
        }
        linkmanSupplierRepository.delete(customer);
        return ReSult.success();
    }
}
