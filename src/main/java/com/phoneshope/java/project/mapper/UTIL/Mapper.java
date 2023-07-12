package com.phoneshope.java.project.mapper.UTIL;

import com.phoneshope.java.project.dto.BrandDto;
import com.phoneshope.java.project.entity.Brand;

public class Mapper {

    public  static Brand toBrand (BrandDto dto){
        Brand brand = new Brand();

        brand.setName(dto.getName());
        return brand;
    }
    public  static  BrandDto toBrandDto(Brand brand){
        BrandDto brandDto = new BrandDto();

        brandDto.setName(brand.getName());
        return brandDto;
    }
}