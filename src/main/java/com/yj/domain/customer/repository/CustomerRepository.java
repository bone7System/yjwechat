package com.yj.domain.customer.repository;

import com.yj.domain.customer.model.Customer;
import com.yj.domain.image.model.Image;
import com.yj.domain.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {
}
