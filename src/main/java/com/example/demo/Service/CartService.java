package com.example.demo.Service;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Entity.CartEntity;
import com.example.demo.Entity.ProductDetailEntity;

import java.util.List;
import java.util.Optional;

public interface CartService {
    public List<CartEntity> findAll(AccountEntity accountEntity);
    public Optional<CartEntity> finByAccountAndProduct(AccountEntity accountEntity, ProductDetailEntity productDetailEntity);
    void addCart(CartEntity cartEntity);
    Integer sumQuantityCart(String userName);
    void deleteCartItem(CartEntity cartEntity);
    CartEntity getById(int id);
}
