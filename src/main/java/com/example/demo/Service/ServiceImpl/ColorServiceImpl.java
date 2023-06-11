package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Entity.ColorEntity;
import com.example.demo.Repository.ColorRepository;
import com.example.demo.Service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<ColorEntity> getAllColor() {
        return colorRepository.findAll();
    }

    @Override
    public ColorEntity getCollorById(int id) {
        Optional<ColorEntity> colorOptional = colorRepository.findById(id);
        ColorEntity colorEntity = null;
        if (colorOptional.isPresent()) {
            colorEntity = colorOptional.get();
        } else {
            throw new RuntimeException("khong tim thay color " + id);
        }
        return colorEntity;
    }

    @Override
    public void saveColor(ColorEntity colorEntity) {
        this.colorRepository.save(colorEntity);
    }

    @Override
    public void deleteColor(ColorEntity colorEntity) {
        this.colorRepository.delete(colorEntity);
    }

    @Override
    public Page<ColorEntity> pageColor(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return colorRepository.findAll(pageable);
    }
}
