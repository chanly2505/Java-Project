package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.ColorDTO;
import com.phoneshope.java.project.entity.Color;
import com.phoneshope.java.project.mapper.ColorMappers;
import com.phoneshope.java.project.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/colors")
@AllArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createColors (@RequestBody ColorDTO colorDTO){
        Color color = ColorMappers.INSTANCE.toColor(colorDTO);
        color= colorService.create(color);
        return ResponseEntity.ok(ColorMappers.INSTANCE.toColorDto(color));
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getColorById(@PathVariable("id") Long colorsId) {
        Color color =  colorService.getById(colorsId);
        return ResponseEntity.ok(ColorMappers.INSTANCE.toColorDto(color));
    }
    @GetMapping("/getallcolor")
    public ResponseEntity<?> listColors() {
        List<ColorDTO> getAllColors = colorService.getAllColors()
                .stream().map(ColorMappers.INSTANCE::toColorDto).toList();
        return ResponseEntity.ok(getAllColors);
    }

 }