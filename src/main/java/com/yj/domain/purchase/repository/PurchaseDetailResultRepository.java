package com.yj.domain.purchase.repository;

import com.yj.domain.purchase.model.PurchaseDetail;
import com.yj.domain.purchase.model.PurchaseDetailResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseDetailResultRepository extends JpaRepository<PurchaseDetailResult,Long>,JpaSpecificationExecutor<PurchaseDetailResult> {
//   @Query(value = "",nativeQuery = true)
//    List<PurchaseDetailResult> findList(Long cgid);
}
