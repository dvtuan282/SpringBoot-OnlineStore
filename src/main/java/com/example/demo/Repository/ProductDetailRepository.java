package com.example.demo.Repository;

import com.example.demo.Entity.ColorEntity;
import com.example.demo.Entity.ProductDetailEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Integer> {
    @Query("SELECT p.color FROM ProductDetailEntity  p where p.product.id =?1 group by p.color")
    List<ColorEntity> findColorByProduct(int id);

    @Query("SELECT p.size FROM ProductDetailEntity  p where p.product.id =?1 group by p.size")
    List<SizeEntity> findSizeByProduct(int id);

    ProductDetailEntity findByProductAndColorAndSize(ProductEntity productEntity, ColorEntity color, SizeEntity size);

    @Query("SELECT sum(pd.quantity) from ProductDetailEntity pd")
    int sumProductD();

}
