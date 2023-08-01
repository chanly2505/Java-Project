package com.phoneshope.java.project.service;

import com.phoneshope.java.project.dto.ModelDTO;
import com.phoneshope.java.project.entity.Model;
import org.springframework.boot.Banner;

import java.util.List;

public interface ModelService {

    Model save(Model model);
     List<Model> getByBrand(Long Id);

     Model getById(Long id);
}
