package com.yj.domain.customer.repository;

import com.yj.domain.customer.model.Customer;
import com.yj.domain.customer.model.LinkmanCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LinkmanCustomerRepository extends JpaRepository<LinkmanCustomer,Long> ,JpaSpecificationExecutor<LinkmanCustomer> {
    List<LinkmanCustomer> findByKaidAndClientOrderByCreateTimeAsc(Long kaid,Long client);

}
