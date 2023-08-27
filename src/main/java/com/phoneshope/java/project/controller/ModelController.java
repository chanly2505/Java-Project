package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.ModelDTO;
import com.phoneshope.java.project.entity.Model;
import com.phoneshope.java.project.exception.ApiException;
import com.phoneshope.java.project.mapper.ModelMappers;
import com.phoneshope.java.project.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;
    private final  ModelMappers modelMappers;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ModelDTO dto) throws ApiException {
        Model model= modelMappers.toModel(dto);
        model =modelService.save(model);
        ModelDTO modelDTO = ModelMappers.INSTANCE.toModelDto(model);
        return  ResponseEntity.ok(modelDTO) ;
    }
}