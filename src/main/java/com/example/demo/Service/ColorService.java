package com.example.demo.Service;

import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Entity.ColorEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColorService {
    List<ColorEntity> getAllColor();
    ColorEntity getCollorById(int id);
    void saveColor(ColorEntity colorEntity);
    void deleteColor(ColorEntity colorEntity);
    public Page<ColorEntity> pageColor(int pageNo, int pageSize);
}
