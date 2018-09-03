package com.yj.domain.customer.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.customer.CustomerDtoC;
import com.yj.pojo.customer.CustomerDtoS;
import com.yj.pojo.customer.CustomerDtoU;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    ReSult addCustomer(CustomerDtoC dto, UserDetail user);

    ReSult updateCustomer( CustomerDtoU dto, UserDetail user) throws YjException;

    ReSult searchCustomer(CustomerDtoS dto, UserDetail user, Pageable pageable);

    ReSult deleteCustomer(Long id, UserDetail user) throws YjException;
}
