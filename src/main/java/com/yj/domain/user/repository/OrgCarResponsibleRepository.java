package com.yj.domain.user.repository;


import com.yj.domain.user.model.OrgCarResponsible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgCarResponsibleRepository extends JpaRepository<OrgCarResponsible, Long> {
    List<OrgCarResponsible> findByResponsibleUserId(Long userId);

    OrgCarResponsible findByResponsibleUserIdAndOrgId(Long userId, Long orgId);

    Integer deleteByResponsibleUserIdAndOrgId(Long userId, Long orgId);
}
