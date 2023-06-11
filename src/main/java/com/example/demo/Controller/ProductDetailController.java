package com.example.demo.Controller;

import com.example.demo.Entity.ProductDetailEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.ServiceImpl.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/product-detail")
public class ProductDetailController {
    @Autowired
    ProductDetailServiceImpl service;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ColorServiceImpl colorService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    SizeServiceImpl sizeService;

    public void detailsDefault(Model model) {
        model.addAttribute("listProduct", productService.showListProdutc());
        model.addAttribute("listColor", colorService.getAllColor());
        model.addAttribute("listSize", sizeService.getAllSize());
        model.addAttribute("listCategory", categoryService.getAllCategory());
    }

    @GetMapping("/list")
    public String showListProductDetail(Model model) {
        return showListProductDetail(model, 1);
    }

    @GetMapping("/list/{pageNo}")
    public String showListProductDetail(Model model, @PathVariable int pageNo) {
        Page<ProductDetailEntity> page = service.page(pageNo - 1, 5);
        List<ProductDetailEntity> listProductD = page.getContent();
        model.addAttribute("listProductD", listProductD);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("productDetail", new ProductDetailEntity());
        detailsDefault(model);
        model.addAttribute("action", "/admin/product-detail/save");
        return "/Admin/productDetail";
    }

    @PostMapping("/save")
    public String saveProductDetail(@ModelAttribute("productDetail") ProductDetailEntity productDetail) {
        service.saveProductD(productDetail);
        return "redirect:/admin/product-detail/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductDetailid(@PathVariable("id") int id) {
        ProductDetailEntity productDetailEntity = service.getProductDById(id);
        service.deleteProductD(productDetailEntity);
        return "redirect:/admin/product-detail/list";
    }

    @GetMapping("/edit")
    public String editProductDetail(@RequestParam int id, Model model) {
        model.addAttribute("productDetail", service.getProductDById(id));
        detailsDefault(model);
        model.addAttribute("action", "/admin/product-detail/update?id=" + id);
        return "/Admin/editProductDetail";
    }

    @PostMapping("/update")
    public String updateProductDetail(@RequestParam int id,
                                      @Valid @ModelAttribute("productDetail") ProductDetailEntity productDetailEntity,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            return "/Admin/editProductDetail";
        }
        productDetailEntity.setId(id);
        service.saveProductD(productDetailEntity);
        return "redirect:/admin/product-detail/list";
    }
}


