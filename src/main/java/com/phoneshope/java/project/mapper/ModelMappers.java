package com.phoneshope.java.project.mapper;

import com.phoneshope.java.project.dto.ModelDTO;
import com.phoneshope.java.project.entity.Model;
import com.phoneshope.java.project.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring",uses = {BrandService.class} )
public interface ModelMappers {

    ModelMappers INSTANCE = Mappers.getMapper(ModelMappers.class);

    @Mapping(target = "brand" , source = "brandId")
    Model toModel(ModelDTO dto);

    @Mapping(target = "brandId" , source = "brand.id")
    ModelDTO toModelDto(Model model);

}
