package com.yj.domain.purchase.repository;

import com.yj.domain.purchase.model.Purchase;
import com.yj.domain.purchase.model.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail,Long>,JpaSpecificationExecutor<PurchaseDetail> {

    PurchaseDetail findByCgidAndRownumAndClient(Long cgid,Long rownum,Long client);

    List<PurchaseDetail> findByCgidAndClient(Long cgid,Long client);
}
