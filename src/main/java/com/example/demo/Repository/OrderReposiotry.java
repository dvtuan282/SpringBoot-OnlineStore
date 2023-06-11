package com.example.demo.Repository;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReposiotry extends JpaRepository<orderEntity, Integer> {
    List<orderEntity> findByStatusOrderByIdDesc(int status);

    List<orderEntity> findByAccountAndStatusOrderByIdDesc(AccountEntity accountEntity, int status);

    Integer countAllByStatus(int status);

}
