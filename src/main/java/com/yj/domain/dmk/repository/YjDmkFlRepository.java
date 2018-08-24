package com.yj.domain.dmk.repository;

import com.yj.domain.dmk.model.YjDmkFlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YjDmkFlRepository extends JpaRepository<YjDmkFlEntity, Integer> {


//    @Query(value = "select r.* from user_role ur left join role r on ur.role_id=r.id where ur.user_id=:userId and r.status = 1 ", nativeQuery = true)
//    List<Role> findRoleByUserId(@Param("userId") Long userId);


}
