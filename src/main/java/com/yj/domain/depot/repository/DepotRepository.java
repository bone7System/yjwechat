package com.yj.domain.depot.repository;

import com.yj.domain.depot.model.Depot;
import com.yj.domain.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepotRepository extends JpaRepository<Depot,Long>{
    List<Depot> findByClientOrClient(Long client,Long client2);
}
