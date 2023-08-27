package com.phoneshope.java.project.repository;

import com.phoneshope.java.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

   Optional<Product> findByModelIdAndColorId(Long modelID , Long ColorId);
}
