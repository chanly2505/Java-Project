package com.phoneshope.java.project.mapper;

import com.phoneshope.java.project.dto.BrandDto;
import com.phoneshope.java.project.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {


    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand toBrand (BrandDto dto);
    BrandDto toBrandDto (Brand brand);
}
