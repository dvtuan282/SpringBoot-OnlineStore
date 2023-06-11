package com.example.demo.Service;

import com.example.demo.Entity.ColorEntity;
import com.example.demo.Entity.ProductDetailEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Entity.SizeEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface ProductDetailService {
    List<ProductDetailEntity> getAllProductDetail();

    ProductDetailEntity getProductDById(int id);

    void saveProductD(ProductDetailEntity productDetailEntity);

    void deleteProductD(ProductDetailEntity productDetailEntity);

    List<ColorEntity> findColorProductId(int id);

    List<SizeEntity> findSizeByProductId(int id);

    public Page<ProductDetailEntity> page(int pageNo, int pageSize);

    public long sumProductD();

    ProductDetailEntity findByProductAndColorAndSize(ProductEntity product, ColorEntity color, SizeEntity size);

}
