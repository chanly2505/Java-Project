package com.phoneshope.java.project.service;

import com.phoneshope.java.project.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface BrandService {
    Brand create(Brand brand);

    Brand getById( Integer id);

    Brand update(Integer id,Brand brandUpdate);


    List<Brand> getAllBrands();

//    List<Brand> getBrands(Map<String, String > params);

    Page<Brand> getBrands(Map<String, String > params);

}
