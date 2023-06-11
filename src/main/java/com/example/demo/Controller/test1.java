package com.example.demo.Controller;

import com.example.demo.Entity.ColorEntity;
import com.example.demo.Service.ServiceImpl.ColorServiceImpl;
import com.example.demo.Service.ServiceImpl.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class test1 {
    @Autowired
    ProductDetailServiceImpl service;

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("show", service.getAllProductDetail());
        return "test1";
    }

//    @GetMapping("/show-detail/{id}")
//    public String showId(Model model, @PathVariable int id){
//        ColorEntity colorEntity = service.getCollorById(id);
//        model.addAttribute("color", colorEntity);
//        return show(model);
//    }
}
