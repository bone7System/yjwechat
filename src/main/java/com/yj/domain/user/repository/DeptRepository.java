package com.yj.domain.user.repository;

import com.yj.domain.user.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeptRepository extends JpaRepository<Dept,Long>{
    @Query(value = "select max(client) from erp_dept",nativeQuery = true)
    Long findbyMaxClient();
}
