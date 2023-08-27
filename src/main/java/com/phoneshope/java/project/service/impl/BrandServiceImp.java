package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.exception.ResourceNotFoundException;
import com.phoneshope.java.project.repository.BrandRepository;
import com.phoneshope.java.project.service.BrandService;
import com.phoneshope.java.project.spec.BrandFilter;
import com.phoneshope.java.project.spec.BrandSpec;
import com.phoneshope.java.project.utill.PageUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Long id) {
        return brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Brand", id)
        );
    }

    @Override
    public Brand update(Long id, Brand brandUpdate) {
        Brand brand = getById(id);
        brand.setName(brandUpdate.getName());
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
//        return brandRepository.findByActiveTrue();
    }

    @Override
    public Page<Brand> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();
        if (params.containsKey("name")) {
            String name = params.get("name");
            brandFilter.setName(name);
        }
        if (params.containsKey("id")) {
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }
        int pageLimit = PageUtill.PAGE_LIMIT;
        if (params.containsKey(PageUtill.PAGE_LIMITS)) {
            pageLimit = Integer.parseInt(params.get(PageUtill.PAGE_LIMITS));
        }
        int pageNumber = PageUtill.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtill.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtill.PAGE_NUMBER));
        }
        BrandSpec brandSpec = new BrandSpec(brandFilter);
        Pageable pageable = PageUtill.getPageable(pageNumber, pageLimit);
        return brandRepository.findAll(pageable);
    }

    @Override
    public void deleted(Long id) {
        Brand brand = getById(id);
        brand.setActive(false);
        brandRepository.save(brand);
        log.info("brand with id = %d is deleted".formatted(id));
    }

}

