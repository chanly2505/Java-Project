package com.phoneshope.java.project.repository;

import com.phoneshope.java.project.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model , Integer> {
    List<Model> findByBrandId(Integer brandId);

}
