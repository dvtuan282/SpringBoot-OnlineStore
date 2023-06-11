package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getByIdCategory(int id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        CategoryEntity categoryEntity = null;
        if (categoryOptional.isPresent()) {
            categoryEntity = categoryOptional.get();
        } else {
            throw new RuntimeException("Khong tim thay category" + id);
        }

        return categoryEntity;
    }

    @Override
    public void saveCategory(CategoryEntity categoryEntity) {
        this.categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategory(CategoryEntity categoryEntity) {
        this.categoryRepository.delete(categoryEntity);
    }

    @Override
    public Page<CategoryEntity> pageCategory(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return categoryRepository.findAll(pageable);
    }
}
