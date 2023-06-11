package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Entity.orderEntity;
import com.example.demo.Repository.OrderReposiotry;
import com.example.demo.Service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OderServiceImpl implements OrderService {
    @Autowired
    OrderReposiotry reposiotry;

    @Override
    public List<orderEntity> finByAll(int status) {
        return reposiotry.findByStatusOrderByIdDesc(status);
    }

    @Override
    public List<orderEntity> findByAccountAndStatus(AccountEntity accountEntity, int status) {
        return reposiotry.findByAccountAndStatusOrderByIdDesc(accountEntity, status);
    }

    @Override
    public orderEntity finById(int id) {
        Optional<orderEntity> optionalOptional = reposiotry.findById(id);
        orderEntity orderEntity = null;
        if (optionalOptional.isPresent()) {
            orderEntity = optionalOptional.get();
        } else {
            throw new RuntimeException("khong tim thay");
        }
        return orderEntity;
    }

    @Override
    public void saveOrder(orderEntity order) {
        this.reposiotry.save(order);

    }

    @Override
    public Integer sumOder(int status) {
        return reposiotry.countAllByStatus(status);
    }
}
