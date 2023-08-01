package com.phoneshope.java.project.mapper;

import com.phoneshope.java.project.dto.ProductDTO;
import com.phoneshope.java.project.dto.ProductImportDto;
import com.phoneshope.java.project.entity.Product;
import com.phoneshope.java.project.entity.ProductImportHistory;
import com.phoneshope.java.project.service.ColorService;
import com.phoneshope.java.project.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "dateImport", source = "importDTO.importDate")
    @Mapping(target = "pricePerUnit", source = "importDTO.importPrice")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "id", ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDto importDTO, Product product);
}