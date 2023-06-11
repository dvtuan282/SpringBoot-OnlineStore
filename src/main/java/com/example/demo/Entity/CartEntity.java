package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "account")
    private AccountEntity account;
    @ManyToOne
    @JoinColumn(name = "product")
    private ProductDetailEntity productDetailEntity;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "total_price")
    private Double totalPrice;

    @Override
    public String toString() {
        return "CartEntity{" +
                "id=" + id +
                ", account=" + account +
                ", productDetailEntity=" + productDetailEntity +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
