package com.yj.domain.purchase.repository;

import com.yj.domain.purchase.model.Take;
import com.yj.domain.purchase.model.TakeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TakeDetailRepository extends JpaRepository<TakeDetail,Long>,JpaSpecificationExecutor<TakeDetail> {
}
