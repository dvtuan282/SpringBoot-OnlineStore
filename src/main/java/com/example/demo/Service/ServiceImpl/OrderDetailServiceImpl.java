package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.OrderDetailEntity;
import com.example.demo.Entity.orderEntity;
import com.example.demo.Repository.OrderDetailRepository;
import com.example.demo.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository repository;
    @Override
    public void save(OrderDetailEntity orderDetailEntity) {
        this.repository.save(orderDetailEntity);
    }

    @Override
    public List<OrderDetailEntity> findByOrder(orderEntity orderEntity) {
        return repository.findByOrder(orderEntity);
    }

    @Override
    public List<OrderDetailEntity> findAllByOderStatus(int status) {
        return repository.findByOrder_Status(status);
    }

    @Override
    public List<OrderDetailEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Object[]> bestSaler() {
        return repository.bestSaler();
    }
}
