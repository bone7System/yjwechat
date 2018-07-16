package com.yj.domain.user.repository;

import com.yj.domain.user.model.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleInfoRepository extends JpaRepository<RoleInfo, Long> {


}
