package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "color")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Please enter your color name")
    @Column(name = "color_name")
    private String colorName;
    @OneToMany(mappedBy = "color", fetch = FetchType.LAZY)
    private List<ProductDetailEntity> productDetails;
}
