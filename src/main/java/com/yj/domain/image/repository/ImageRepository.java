package com.yj.domain.image.repository;

import com.yj.domain.image.model.Image;
import com.yj.domain.user.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image,Long>{

}
