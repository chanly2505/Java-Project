package com.phoneshope.java.project.repository;

import com.phoneshope.java.project.entity.Product;
import com.phoneshope.java.project.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportRepository extends JpaRepository<ProductImportHistory, Long> {

}
