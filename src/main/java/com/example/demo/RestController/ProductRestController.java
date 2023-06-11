package com.example.demo.RestController;

import com.example.demo.DTO.ProductDto;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.ServiceImpl.ProductServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class ProductRestController {
//    @Autowired
//    ProductServiceImpl service;
//    HttpServletRequest httpServletRequest;
//
//    @GetMapping("/edit-product/{id}")
//    public ProductDto findProductByID(@PathVariable(value = "id") int id) {
//        Optional<ProductEntity> productOptional = service.getProductById(id);
//        ProductDto productDto = null;
//        if (productOptional.isPresent()) {
//            ProductEntity productEntity = productOptional.get();
//            File file = new File("/src/main/resources/image/" + productEntity.getImage());
//            FileInputStream inputStream;
//            try {
//                inputStream = new FileInputStream((file));
//                MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "txt/plain",
//                        IOUtils.toByteArray(inputStream));
//                productDto = new ProductDto(productEntity.getId(), productEntity.getProductName(), productDto.getStatus(), multipartFile);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return productDto;
//    }
}
