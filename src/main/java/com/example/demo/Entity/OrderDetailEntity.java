package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "order")
    private orderEntity order;
    @ManyToOne
    @JoinColumn(name = "product")
    private ProductDetailEntity productDetailEntity;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "amount")
    private Double amount;
}
