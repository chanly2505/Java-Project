package com.phoneshope.java.project.repository;

import com.phoneshope.java.project.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long> , JpaSpecificationExecutor<SaleDetail> {

    List<SaleDetail> findBySaleId(Long id);
}
