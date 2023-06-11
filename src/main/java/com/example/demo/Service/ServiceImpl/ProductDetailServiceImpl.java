package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.ColorEntity;
import com.example.demo.Entity.ProductDetailEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Entity.SizeEntity;
import com.example.demo.Repository.ProductDetailRepository;
import com.example.demo.Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository repository;

    @Override
    public List<ProductDetailEntity> getAllProductDetail() {
        return repository.findAll();
    }

    @Override
    public ProductDetailEntity getProductDById(int id) {
        Optional<ProductDetailEntity> productDetailOptional = repository.findById(id);
        ProductDetailEntity productDetailEntity = null;
        if (productDetailOptional.isPresent()) {
            productDetailEntity = productDetailOptional.get();
        } else {
            throw new RuntimeException("Khong tim thay productDetail: " + id);
        }
        return productDetailEntity;
    }

    @Override
    public void saveProductD(ProductDetailEntity productDetailEntity) {
        this.repository.save(productDetailEntity);
    }

    @Override
    public void deleteProductD(ProductDetailEntity productDetailEntity) {
        this.repository.delete(productDetailEntity);
    }

    @Override
    public List<ColorEntity> findColorProductId(int id) {
        return repository.findColorByProduct(id);
    }

    @Override
    public List<SizeEntity> findSizeByProductId(int id) {
        return repository.findSizeByProduct(id);
    }

    @Override
    public Page<ProductDetailEntity> page(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return repository.findAll(pageable);
    }

    @Override
    public long sumProductD() {
        return repository.sumProductD();
    }

    @Override
    public ProductDetailEntity findByProductAndColorAndSize(ProductEntity product, ColorEntity color, SizeEntity size) {
        return repository.findByProductAndColorAndSize(product, color, size);
    }


}