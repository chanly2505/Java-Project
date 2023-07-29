package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.BrandDto;
import com.phoneshope.java.project.dto.ModelDTO;
import com.phoneshope.java.project.dto.PageDTO;
import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.entity.Model;
import com.phoneshope.java.project.mapper.BrandMapper;
import com.phoneshope.java.project.mapper.ModelMappers;
import com.phoneshope.java.project.service.BrandService;
import com.phoneshope.java.project.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@RestController
@RequestMapping("/brand")
public class BrandController {


    private final BrandService brandService;

    private final ModelService modelService;

    private final ModelMappers modelMappers;

    @RequestMapping(method = RequestMethod.POST)
     public ResponseEntity<?> create (@RequestBody BrandDto brandDto){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDto);
         brand= brandService.create(brand);
         return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
     }
    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand (@PathVariable("id") Integer brandId){
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
    }
    @PutMapping("{id}")
    public ResponseEntity<?> update (@PathVariable("id") Integer brandId, @RequestBody BrandDto brandDto){
        Brand brand= BrandMapper.INSTANCE.toBrand(brandDto);
        Brand brandUpdated= brandService.update(brandId,brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brandUpdated));

    }
    @GetMapping("/getallbrand")
    public ResponseEntity<?> list(){
        List<BrandDto> listBrand = brandService.getAllBrands()
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDto)
                .toList();

        return ResponseEntity.ok(listBrand);
    }
    @GetMapping
    public ResponseEntity<?> getBrandsByName (@RequestParam Map<String , String > params){
        Page<Brand> brands = brandService.getBrands(params);
        PageDTO pageDTO = new PageDTO(brands);
        return ResponseEntity.ok(pageDTO);
    }
    @GetMapping("{id}/models")
    public ResponseEntity<?> getModelByBrand (@PathVariable("id") Integer brandId){
        List<Model> brand= modelService .getByBrand(brandId);
        List<ModelDTO> list=brand.stream().map(modelMappers::toModelDto).toList();
        return ResponseEntity.ok(list);
    }
}