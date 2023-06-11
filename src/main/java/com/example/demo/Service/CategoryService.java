package com.example.demo.Service;

import com.example.demo.Entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public List<CategoryEntity> getAllCategory();
    public CategoryEntity getByIdCategory(int id);
    void saveCategory(CategoryEntity categoryEntity);
    void deleteCategory(CategoryEntity categoryEntity);
    public Page<CategoryEntity> pageCategory( int pageNo, int pageSize);

}
