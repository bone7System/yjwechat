package com.yj.domain.supplier.repository;

import com.yj.domain.customer.model.Customer;
import com.yj.domain.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SupplierRepository extends JpaRepository<Supplier,Long>,JpaSpecificationExecutor<Supplier> {
}
