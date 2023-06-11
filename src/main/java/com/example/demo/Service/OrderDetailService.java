package com.example.demo.Service;

import com.example.demo.Entity.OrderDetailEntity;
import com.example.demo.Entity.orderEntity;

import java.util.List;

public interface OrderDetailService {
    void save(OrderDetailEntity orderDetailEntity);
    List<OrderDetailEntity> findByOrder(orderEntity orderEntity);
    List<OrderDetailEntity> findAllByOderStatus(int status);

    List<OrderDetailEntity> findAll();

    List<Object[]> bestSaler();
}
