package com.yj.domain.user.repository;

import com.yj.domain.user.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query(value = "select * from erp_permission\n" +
            "\t where id in (\n" +
            "\t\tselect permissionid from erp_role_permission where roleid in (\n" +
            "\t\t\n" +
            "\t\tselect roleid from erp_user_role where userid = :userId\n" +
            "\t )\n" +
            " )", nativeQuery = true)
    List<Permission> findPermissionByUserId(@Param("userId") Long userId);

    /**
     *  父节点下的所有子节点
     * @param id
     * @return
     */
    List<Permission> findByParentId(Long id);


    List<Permission> findByPermissionIn(Set<String> pps);

}
