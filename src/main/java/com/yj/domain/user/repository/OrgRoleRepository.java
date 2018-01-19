package com.yj.domain.user.repository;

import com.yj.domain.user.model.OrgRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRoleRepository extends JpaRepository<OrgRole, Long> {

    OrgRole save(OrgRole orgRole);

}
