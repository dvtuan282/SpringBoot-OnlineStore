package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductEntity> showListProdutc() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Integer id) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);
        ProductEntity productEntity = null;
        if (productOpt.isPresent()) {
            productEntity = productOpt.get();
        } else {
            throw new RuntimeException("Khong tim thay product" + id);
        }
        return productEntity;
    }

    @Override
    public void saveProduct(ProductEntity productEntity) {
        this.productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Page<ProductEntity> showListProductPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<ProductEntity> findByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory_CategoryName(category, pageable);
    }

    @Override
    public Page<ProductEntity> findByProductName(String productName, Pageable pageable) {
        return productRepository.findByProductNameLike(productName, pageable);
    }

    @Override
    public Page<ProductEntity> findByPriceBetween(Double min, Double max, Pageable pageable) {
        return productRepository.findByPriceBetween(min, max, pageable);
    }


}
