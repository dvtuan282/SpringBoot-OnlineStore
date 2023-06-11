package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Entity.CartEntity;
import com.example.demo.Entity.ProductDetailEntity;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public List<CartEntity> findAll(AccountEntity accountEntity) {
        return cartRepository.findByAccountOrderByIdDesc(accountEntity);
    }

    @Override
    public Optional<CartEntity> finByAccountAndProduct(AccountEntity accountEntity, ProductDetailEntity productDetailEntity) {
        return cartRepository.findByAccountAndProductDetailEntity(accountEntity, productDetailEntity);
    }

    @Override
    public CartEntity getById(int id) {
        Optional<CartEntity> cartEntityOptional = cartRepository.findById(id);
        CartEntity cartEntity = null;
        if (cartEntityOptional.isPresent()) {
            cartEntity = cartEntityOptional.get();
        } else {
            throw new RuntimeException("khong tim thay");
        }
        return cartEntity;
    }

    @Override
    public void addCart(CartEntity cartEntity) {
        this.cartRepository.save(cartEntity);
    }

    @Override
    public Integer sumQuantityCart(String userName) {
        return cartRepository.sumCart(userName);
    }

    @Override
    public void deleteCartItem(CartEntity cartEntity) {
        this.cartRepository.delete(cartEntity);
    }
}
