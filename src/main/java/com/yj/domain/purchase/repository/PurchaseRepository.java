package com.yj.domain.purchase.repository;

import com.yj.domain.purchase.model.Purchase;
import com.yj.domain.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseRepository extends JpaRepository<Purchase,Long>,JpaSpecificationExecutor<Purchase> {
}
