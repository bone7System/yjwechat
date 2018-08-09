package com.yj.domain.user.repository;

import com.yj.domain.user.model.Dept;
import com.yj.domain.user.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface MenuRepository extends JpaRepository<Menu,Long>{
    List<Menu> findByIdIn(Set<Long> ids);
}
