package com.example.demo.Repository;

import com.example.demo.Entity.OrderDetailEntity;
import com.example.demo.Entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    List<OrderDetailEntity> findByOrder(orderEntity orderEntity);

    List<OrderDetailEntity> findByOrder_Status(int status);
    @Query("SELECT od.productDetailEntity.product.productName, " +
            "od.productDetailEntity.product.price, " +
            "od.productDetailEntity.product.image1, " +
            "SUM(od.quantity) AS total " +
            "FROM OrderDetailEntity od " +
            "GROUP BY od.productDetailEntity.product.productName, " +
            "od.productDetailEntity.product.price, " +
            "od.productDetailEntity.product.image1 " +
            "ORDER BY total DESC")
    List<Object[]> bestSaler();
}
