package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.entity.Color;
import com.phoneshope.java.project.exception.ResourceNotFoundException;
import com.phoneshope.java.project.repository.ColorRepository;
import com.phoneshope.java.project.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ColorServiceImpl implements ColorService {

    private  final ColorRepository colorRepository;

    @Override
    public Color create(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Color",id));
    }
}