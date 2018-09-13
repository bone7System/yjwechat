package com.yj.domain.purchase.repository;

import com.yj.domain.purchase.model.Purchase;
import com.yj.domain.purchase.model.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail,Long>,JpaSpecificationExecutor<PurchaseDetail> {
}
