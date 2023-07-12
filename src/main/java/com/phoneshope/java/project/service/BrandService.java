package com.phoneshope.java.project.service;

import com.phoneshope.java.project.entity.Brand;
import org.springframework.stereotype.Service;


public interface BrandService {
    Brand create(Brand brand);

    Brand getById(Integer id);

    Brand update(Integer id,Brand brandUpdate);

}
