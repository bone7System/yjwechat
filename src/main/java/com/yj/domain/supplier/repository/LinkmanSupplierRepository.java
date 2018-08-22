package com.yj.domain.supplier.repository;

import com.yj.domain.customer.model.LinkmanCustomer;
import com.yj.domain.supplier.model.LinkmanSupplier;
import com.yj.domain.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkmanSupplierRepository extends JpaRepository<LinkmanSupplier,Long>,JpaSpecificationExecutor<LinkmanSupplier> {
}
