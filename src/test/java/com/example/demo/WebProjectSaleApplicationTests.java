package com.example.demo;

import com.example.demo.Entity.ProductEntity;
import com.example.demo.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class WebProjectSaleApplicationTests {
    @Autowired
    ProductRepository repository;

    @Test
    void contextLoads() {
    }

}
