package com.example.demo.DTO;

import com.example.demo.Entity.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;

    @NotBlank(message = "Please enter your product name")
    private String productName;

    private Double price;

    private Integer status;

    private MultipartFile image1;

    private MultipartFile image2;

    private MultipartFile image3;

    private String description;

    private int categoryId;
}
