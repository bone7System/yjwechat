package com.yj.domain.supplier.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.supplier.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface SupplierService {
    ReSult addSupplier(SupplierDtoC supplierDtoC, UserDetail user);

    ReSult updateSupplier(SupplierDtoU supplierDtoU, UserDetail user) throws YjException;

    ReSult searchSupplier(SupplierDtoS supplierDtoS, UserDetail user, Pageable pageable);

    ReSult deleteSupplier(Long id, UserDetail user) throws YjException;

    ReSult addSpplierSpplier( SupplierLinkManDtoC dto, UserDetail user);

    ReSult updateCustomerSpplier(SupplierLinkManDtoU dto, UserDetail user) throws YjException;

    ReSult searchCustomerSpplier(Long khbh, UserDetail user);

    ReSult deleteLinkManSpplier(Long id, UserDetail user) throws YjException;

    ReSult getById(Long id);

    ReSult getLinkManById(Long id);
}
