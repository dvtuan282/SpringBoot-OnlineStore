package com.example.demo.Service;

import com.example.demo.Entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<ProductEntity> showListProdutc();

    public ProductEntity getProductById(Integer id);

    void saveProduct(ProductEntity productEntity);

    void deleteProduct(Integer id);

    public Page<ProductEntity> showListProductPage(int pageNo, int pageSize);

    public Page<ProductEntity> findByCategory(String category, Pageable pageable);

    public Page<ProductEntity> findByProductName(String productName, Pageable pageable);

    public Page<ProductEntity> findByPriceBetween(Double min, Double max, Pageable pageable);
}
