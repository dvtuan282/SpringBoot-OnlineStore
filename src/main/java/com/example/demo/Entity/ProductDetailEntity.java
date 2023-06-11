package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product_details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "color")
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "size")
    private SizeEntity size;

    @NotNull(message = "Please enter your quantity")
    @Min(value = 1, message = "Quantity greater than 1")
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "productDetailEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartEntity> cartEntitiess;
    @OneToMany(mappedBy = "productDetailEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetailEntities;

    @Override
    public String toString() {
        return "ProductDetailEntity{" +
                "id=" + id +
                ", product=" + product +
                ", color=" + color +
                ", size=" + size +
                ", quantity=" + quantity +
                ", status=" + status +

                '}';
    }
}
