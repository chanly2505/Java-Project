package com.phoneshope.java.project.service;


import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.entity.Color;

import java.util.List;

public interface ColorService {
    Color create(Color color);

    Color getById( Long id);

    List<Color> getAllColors();

//    Brand update(Long id,Brand brandUpdate);
//
//
//    List<Brand> getAllBrands();
//
////    List<Brand> getBrands(Map<String, String > params);
//
//    Page<Brand> getBrands(Map<String, String > params);

}
