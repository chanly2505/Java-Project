package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.BrandDto;
import com.phoneshope.java.project.dto.ModelDTO;
import com.phoneshope.java.project.dto.PageDTO;
import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.entity.Model;
import com.phoneshope.java.project.exception.ApiException;
import com.phoneshope.java.project.mapper.BrandMapper;
import com.phoneshope.java.project.mapper.ModelMappers;
import com.phoneshope.java.project.service.BrandService;
import com.phoneshope.java.project.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;
    @PostMapping
     public ResponseEntity<?> create (@RequestBody BrandDto brandDto)throws  ApiException{
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDto);
         brand= brandService.create(brand);
         return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
     }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand (@PathVariable("id") Long brandId){
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
    }
    @PutMapping("{id}")
    public ResponseEntity<?> update (@PathVariable("id") Long brandId, @RequestBody BrandDto brandDto) throws  ApiException{
        Brand brand= BrandMapper.INSTANCE.toBrand(brandDto);
        Brand brandUpdated= brandService.update(brandId,brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brandUpdated));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedById (@PathVariable("id") Long id) throws ApiException {
        brandService.deleted(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getallbrand")
    public ResponseEntity<?> list() throws ApiException{
        List<BrandDto> listBrand = brandService.getAllBrands()
                .stream()
                .map(BrandMapper.INSTANCE::toBrandDto)
                .toList();

        return ResponseEntity.ok(listBrand);
    }

    @GetMapping
    public ResponseEntity<?> getBrandsByName (@RequestParam Map<String , String > params) throws  ApiException {
        Page<Brand> brands = brandService.getBrands(params);
        PageDTO pageDTO = new PageDTO(brands);
        return ResponseEntity.ok(pageDTO);
    }
    @GetMapping("{id}/models")
    public ResponseEntity<?> getModelByBrand (@PathVariable("id") Long brandId){
        List<Model> brand= modelService .getByBrand(brandId);
        List<ModelDTO> list=brand.stream().map(ModelMappers.INSTANCE::toModelDto).toList();
        return ResponseEntity.ok(list);
    }

}