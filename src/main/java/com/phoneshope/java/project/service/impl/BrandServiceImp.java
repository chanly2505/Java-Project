package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.exception.ApiException;
import com.phoneshope.java.project.exception.ResourceNotFoundException;
import com.phoneshope.java.project.repository.BrandRepository;
import com.phoneshope.java.project.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand create(Brand brand) {
         return  brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer id) {
//        Optional<Brand> optionalBrand = brandRepository.findById(id);
//        if (optionalBrand.isPresent()){
//            return optionalBrand.get();
//        }else {
//            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format( "Brand Id =%d Not Found", id));
//        }
        return  brandRepository.findById(id).orElseThrow(
                //() ->  new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format( "Brand Id =%d Not Found", id))
                () ->  new ResourceNotFoundException("Brand", id)
        );
    }

    @Override
    public Brand update(Integer id,Brand brandUpdate) {
        Brand brand=getById(id);
        brand.setName(brandUpdate.getName());
        return brandRepository.save(brand);
    }
}