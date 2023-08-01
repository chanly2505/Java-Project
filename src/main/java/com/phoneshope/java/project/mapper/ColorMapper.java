package com.phoneshope.java.project.mapper;

import com.phoneshope.java.project.dto.ColorDTO;
import com.phoneshope.java.project.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapper {

    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    Color toColor(ColorDTO colorDTO);

    ColorDTO toEntity(Color color);
}
