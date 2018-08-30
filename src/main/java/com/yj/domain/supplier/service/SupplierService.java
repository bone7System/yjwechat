package com.yj.domain.supplier.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.ReSult;
import com.yj.pojo.supplier.SupplierDtoC;
import com.yj.pojo.supplier.SupplierDtoS;
import com.yj.pojo.supplier.SupplierDtoU;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    ReSult addSupplier(SupplierDtoC supplierDtoC, UserDetail user);

    ReSult updateSupplier(SupplierDtoU supplierDtoU, UserDetail user) throws YjException;

    ReSult searchSupplier(SupplierDtoS supplierDtoS, UserDetail user, Pageable pageable);

    ReSult deleteSupplier(Long id, UserDetail user) throws YjException;
}
