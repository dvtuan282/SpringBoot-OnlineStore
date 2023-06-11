package com.example.demo.Repository;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Entity.CartEntity;
import com.example.demo.Entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    List<CartEntity> findByAccountOrderByIdDesc(AccountEntity accountEntity);
    Optional<CartEntity> findByAccountAndProductDetailEntity(AccountEntity accountEntity, ProductDetailEntity productDetailEntity);
    @Query("SELECT sum(c.quantity) from CartEntity c where c.account.userName=?1")
    Integer sumCart(String userName);
}
