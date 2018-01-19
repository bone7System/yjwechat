package com.yj.domain.user.repository;

import com.yj.domain.user.model.OrgUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgUserRepository extends JpaRepository<OrgUser, Long> {
    Integer deleteAllByUserId(Long userId);
}
