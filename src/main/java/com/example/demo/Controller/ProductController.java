package com.example.demo.Controller;

import com.example.demo.DTO.ProductDto;
import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.ServiceImpl.CategoryServiceImpl;
import com.example.demo.Service.ServiceImpl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    private static final String UPLOAD_DIR = "src/resources/static/image/";
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public String showListProduct(Model model) {
        return showListProduct(model, 1);
    }

    @GetMapping("/list/{pageNo}")
    public String showListProduct(Model model, @PathVariable("pageNo") int pageNo) {
        int pageSize = 5;
        Page<ProductEntity> page = productService.showListProductPage(pageNo - 1, pageSize);
        List<ProductEntity> product = page.getContent();
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("listProduct", product);
        model.addAttribute("listCategory", categoryService.getAllCategory());
        model.addAttribute("product", new ProductDto());
        model.addAttribute("action", "/admin/product/save");
        return "/Admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") int id, Model model) {
        ProductEntity productEntity = productService.getProductById(id);
        productService.deleteProduct(id);
        return "redirect:/admin/product/list";
    }

    @GetMapping("/edit")
    public String updateProduct(@RequestParam int id, Model model) {
        ProductEntity productEntity = productService.getProductById(id);
        ProductDto productDto = null;
        File file1 = new File("src/main/resources/static/img/" + productEntity.getImage1());
        File file2 = new File("src/main/resources/static/img/" + productEntity.getImage2());
        File file3 = new File("src/main/resources/static/img/" + productEntity.getImage3());
        FileInputStream inputStream3;
        FileInputStream inputStream1;
        FileInputStream inputStream2;
        try {
            inputStream1 = new FileInputStream((file1));
            MultipartFile multipartFile1 = new MockMultipartFile("file", file1.getName(), "txt/plain",
                    IOUtils.toByteArray(inputStream1));
            inputStream2 = new FileInputStream((file2));
            MultipartFile multipartFile2 = new MockMultipartFile("file", file2.getName(), "txt/plain",
                    IOUtils.toByteArray(inputStream2));
            inputStream3 = new FileInputStream((file3));
            MultipartFile multipartFile3 = new MockMultipartFile("file", file3.getName(), "txt/plain",
                    IOUtils.toByteArray(inputStream3));
            productDto = new ProductDto(productEntity.getId(), productEntity.getProductName(),productEntity.getPrice(), productEntity.getStatus(),
                    multipartFile1,multipartFile2,multipartFile3,productEntity.getDescription(),productEntity.getCategory().getId());
            model.addAttribute("product", productDto);
            model.addAttribute("category", categoryService.getAllCategory());
            model.addAttribute("action", "/admin/product/update?id="+id);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/Admin/editProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute ProductDto productDto,@RequestParam int id){
        ProductEntity productEntity = null;
        productEntity = convertToEntity(productDto);
        productEntity.setId(id);
        productService.saveProduct(productEntity);
        return "redirect:/admin/product/list";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result, Model model) {
//            add new product
        if (result.hasErrors()) {
            return "redirect:/admin/product/list";
        } else {
            ProductEntity productEntity = null;
            productEntity = convertToEntity(productDto);
            productService.saveProduct(productEntity);
            return "redirect:/admin/product/list";
        }
    }

    public ProductEntity convertToEntity(ProductDto productDto) {
//        Khai baod đường dẫn ảnh
        String image1 = "";
        String image2 = "";
        String image3 = "";
//        Khai baod đường dẫn ảnh
        Path path = Paths.get("src/main/resources/static/img");
        try {
            InputStream inputStream1 = productDto.getImage1().getInputStream();
            Files.copy(inputStream1, path.resolve(productDto.getImage1().getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            image1 = productDto.getImage1().getOriginalFilename().toString();

            InputStream inputStream2 = productDto.getImage2().getInputStream();
            Files.copy(inputStream2, path.resolve(productDto.getImage2().getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            image2 = productDto.getImage2().getOriginalFilename().toString();

            InputStream inputStream3 = productDto.getImage3().getInputStream();
            Files.copy(inputStream3, path.resolve(productDto.getImage3().getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            image3 = productDto.getImage3().getOriginalFilename().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
//      Convert Class Dto sang Entity
        ProductEntity productEntity = new ProductEntity();
        CategoryEntity categoryEntity = categoryService.getByIdCategory(productDto.getCategoryId());
        productEntity.setId(productDto.getId());
        productEntity.setProductName(productDto.getProductName());
        productEntity.setImage1(image1);
        productEntity.setImage2(image2);
        productEntity.setImage3(image3);
        productEntity.setPrice(productDto.getPrice());
        productEntity.setStatus(productDto.getStatus());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setCategory(categoryEntity);
        return productEntity;
    }
}
