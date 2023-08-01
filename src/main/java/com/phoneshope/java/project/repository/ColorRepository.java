package com.phoneshope.java.project.repository;

import com.phoneshope.java.project.entity.Color;
import com.phoneshope.java.project.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {


}
