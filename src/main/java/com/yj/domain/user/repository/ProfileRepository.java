package com.yj.domain.user.repository;

import com.yj.domain.user.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUserId(Long userId);
}
