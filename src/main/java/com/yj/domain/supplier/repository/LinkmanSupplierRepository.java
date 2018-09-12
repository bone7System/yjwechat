package com.yj.domain.supplier.repository;

import com.yj.domain.customer.model.LinkmanCustomer;
import com.yj.domain.supplier.model.LinkmanSupplier;
import com.yj.domain.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LinkmanSupplierRepository extends JpaRepository<LinkmanSupplier,Long>,JpaSpecificationExecutor<LinkmanSupplier> {
    List findByLfidAndClientOrderByCreateTimeAsc(Long khbh, Long client);
}
