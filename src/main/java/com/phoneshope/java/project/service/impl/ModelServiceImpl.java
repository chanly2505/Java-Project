package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.dto.ModelDTO;
import com.phoneshope.java.project.entity.Model;
import com.phoneshope.java.project.mapper.ModelMappers;
import com.phoneshope.java.project.repository.BrandRepository;
import com.phoneshope.java.project.repository.ModelRepository;
import com.phoneshope.java.project.service.BrandService;
import com.phoneshope.java.project.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMappers modelMapper;

    @Override
    public Model save(Model model) {
//        Model model= modelMapper.toModel(dto);
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getByBrand(Integer brandId) {
        return modelRepository.findByBrandId(brandId);
    }
}