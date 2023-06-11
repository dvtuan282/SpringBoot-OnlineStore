package com.example.demo.Repository;

import com.example.demo.Entity.ProductDetailEntity;
import com.example.demo.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Page<ProductEntity> findByCategory_CategoryName(String category, Pageable pageable);
    Page<ProductEntity> findByProductNameLike(String productName, Pageable pageable);
    Page<ProductEntity> findByPriceBetween(Double min, Double max, Pageable pageable);
}
