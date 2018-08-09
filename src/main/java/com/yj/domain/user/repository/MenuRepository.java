package com.yj.domain.user.repository;

import com.yj.domain.user.model.Dept;
import com.yj.domain.user.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository extends JpaRepository<Menu,Long>{

}
