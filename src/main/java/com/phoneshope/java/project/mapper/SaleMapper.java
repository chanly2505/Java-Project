package com.phoneshope.java.project.mapper;

import com.phoneshope.java.project.dto.SaleDTO;
import com.phoneshope.java.project.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleMapper {

    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    Sale toSaleDto(SaleDTO saleDTO);

    SaleDTO toEntity(Sale sale);
}
