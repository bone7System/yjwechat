package com.yj.domain.dmk.repository;

import com.yj.domain.dmk.model.YjDmkClEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface YjDmkClRepository extends JpaRepository<YjDmkClEntity, Integer> {


    void deleteByDmbzAndDm(@Param("dmbz") String dmbz, @Param("dm") String dm);


//    @Query(value = "select r.* from user_role ur left join role r on ur.role_id=r.id where ur.user_id=:userId and r.status = 1 ", nativeQuery = true)
//    List<Role> findRoleByUserId(@Param("userId") Long userId);


}
