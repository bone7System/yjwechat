package com.yj.domain.customer.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.customer.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface CustomerService {

    ReSult addCustomer(CustomerDtoC dto, UserDetail user);

    ReSult updateCustomer( CustomerDtoU dto, UserDetail user) throws YjException;

    ReSult searchCustomer(CustomerDtoS dto, UserDetail user, Pageable pageable);

    ReSult deleteCustomer(Long id, UserDetail user) throws YjException;

    ReSult addCustomerLinkMan( CustomerLinkManDtoC dto, UserDetail user);

    ReSult updateCustomerLinkMan(CustomerLinkManDtoU dto, UserDetail user) throws YjException;

    ReSult searchCustomerLinkMan(Long khbh, UserDetail user);

    ReSult deleteLinkManCustomer(Long id, UserDetail user) throws YjException;

    ReSult getLinkManById(Long id);

    ReSult getById(Long id);
}
