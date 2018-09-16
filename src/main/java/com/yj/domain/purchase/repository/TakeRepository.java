package com.yj.domain.purchase.repository;

import com.yj.domain.purchase.model.Purchase;
import com.yj.domain.purchase.model.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TakeRepository extends JpaRepository<Take,Long>,JpaSpecificationExecutor<Take> {
}
