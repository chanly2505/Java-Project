package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.BrandDto;
import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.mapper.BrandMapper;
import com.phoneshope.java.project.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

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

}