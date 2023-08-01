package com.phoneshope.java.project.service;


import com.phoneshope.java.project.entity.Color;

public interface ColorService {
    Color create(Color color);

    Color getById( Long id);

//    Brand update(Long id,Brand brandUpdate);
//
//
//    List<Brand> getAllBrands();
//
////    List<Brand> getBrands(Map<String, String > params);
//
//    Page<Brand> getBrands(Map<String, String > params);

}
