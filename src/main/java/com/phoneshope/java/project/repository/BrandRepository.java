package com.phoneshope.java.project.repository;

import com.phoneshope.java.project.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand , Integer> {
}
