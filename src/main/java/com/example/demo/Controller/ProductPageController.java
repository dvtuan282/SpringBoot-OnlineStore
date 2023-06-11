package com.example.demo.Controller;

import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.ProductService;
import com.example.demo.Service.ServiceImpl.CartServiceImpl;
import com.example.demo.Service.ServiceImpl.CategoryServiceImpl;
import com.example.demo.Service.ServiceImpl.ProductDetailServiceImpl;
import com.example.demo.Service.ServiceImpl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("categoriesInfo")
@RequestMapping("/user")
public class ProductPageController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductDetailServiceImpl productDetailService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    CartServiceImpl cartService;

    @ModelAttribute("categoriesInfo")
    private List<CategoryEntity> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/product")
    public String showProductPage(Model model,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  HttpSession session
    ) {
        int pageSize = 8;
        Page<ProductEntity> page = productService.showListProductPage(pageNum - 1, pageSize);
        List<ProductEntity> product = page.getContent();
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("pageNo", pageNum);
        model.addAttribute("listProduct", product);
//        model.addAttribute("itemPage", page.getTotalElements());
        String name = (String) session.getAttribute("name");
        model.addAttribute("name", name);
        model.addAttribute("sumCart", cartService.sumQuantityCart(name));
        return "productPage";
    }

    @GetMapping("/product-detail/{id}")
    public String showDetailProduct(Model model, @PathVariable int id,
                                    HttpSession session) {
        ProductEntity productEntity = productService.getProductById(id);
        model.addAttribute("size", productDetailService.findSizeByProductId(id));
        model.addAttribute("color", productDetailService.findColorProductId(id));
        model.addAttribute("productDetail", productEntity);
        String name = (String) session.getAttribute("name");
        model.addAttribute("name", name);
        model.addAttribute("sumCart", cartService.sumQuantityCart(name));
        return "productDetail";
    }

    @GetMapping("/find-category")
    public String findByCategory(Model model,
                                 @RequestParam String category,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum
    ) {
        Pageable pageable = PageRequest.of(pageNum - 1, 8);
        Page<ProductEntity> page = productService.findByCategory(category, pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("pageNo", pageNum);
        model.addAttribute("listProduct", page.getContent());
        return "productPage";
    }

    @GetMapping("/product/search")
    public String findByProductName(@RequestParam("keyWord") String productName,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    HttpSession session,
                                    Model model) {
        session.setAttribute("keyWord", productName);
        String productNameS = (String) session.getAttribute("keyWord");
        model.addAttribute("keyWord", productNameS);
        Pageable pageable = PageRequest.of(pageNum - 1, 2);
        Page<ProductEntity> page = productService.findByProductName("%" + productName + "%", pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("pageNo", pageNum);
        model.addAttribute("listProduct", page.getContent());
        return "productPage";
    }

    @GetMapping("/product/price")
    public String finByPriceBetween(@RequestParam("min") Double min,
                                    @RequestParam("max") Double max,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    HttpSession session,
                                    Model model
    ) {
        session.setAttribute("min", min);
        session.setAttribute("max", max);
        Double minS = (Double) session.getAttribute("min");
        Double maxS = (Double) session.getAttribute("max");
        model.addAttribute("min", minS);
        model.addAttribute("max", maxS);
        Pageable pageable = PageRequest.of(pageNum - 1, 8);
        Page<ProductEntity> page = productService.findByPriceBetween(min, max, pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("pageNo", pageNum);
        model.addAttribute("listProduct", page.getContent());
        return "productPage";
    }

}
