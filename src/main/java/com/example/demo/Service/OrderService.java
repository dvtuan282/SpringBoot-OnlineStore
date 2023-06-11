package com.example.demo.Service;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Entity.orderEntity;
import jakarta.persistence.criteria.Order;

import java.util.List;

public interface OrderService {
    public List<orderEntity> finByAll(int status);
    public List<orderEntity> findByAccountAndStatus(AccountEntity accountEntity, int status);
    public orderEntity finById(int id);
    void saveOrder(orderEntity order);
    Integer sumOder(int status);
}
