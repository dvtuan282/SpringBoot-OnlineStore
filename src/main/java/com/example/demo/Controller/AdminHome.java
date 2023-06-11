package com.example.demo.Controller;

import com.example.demo.Entity.OrderDetailEntity;
import com.example.demo.Entity.ProductDetailEntity;
import com.example.demo.Entity.orderEntity;
import com.example.demo.Service.ServiceImpl.OderServiceImpl;
import com.example.demo.Service.ServiceImpl.OrderDetailServiceImpl;
import com.example.demo.Service.ServiceImpl.ProductDetailServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHome {
    @Autowired
    ProductDetailServiceImpl productDetailService;
    @Autowired
    OderServiceImpl oderService;
    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @GetMapping("/home")
    public String adminHome(Model model, HttpSession session) {
        String name = (String) session.getAttribute("name");
        model.addAttribute("name", name);
        model.addAttribute("totalProductD", productDetailService.sumProductD());
        oder(model);
        totalRevenue(model);
        bestSeller(model);
        return "/Admin/index";
    }

    public void totalRevenue(Model model) {
        double sum = 0;
        for (OrderDetailEntity od : orderDetailService.findAll()
        ) {
            if (od.getOrder().getStatus() == 1) {
                sum += od.getAmount();
            }
        }
        model.addAttribute("totalRevenue", sum);
    }

    public void oder(Model model){
        model.addAttribute("orderOke", oderService.sumOder(1));
        model.addAttribute("orderNo", oderService.sumOder(2));
    }

    public void bestSeller(Model model){
        model.addAttribute("bestSaler", orderDetailService.bestSaler());
        for (int i = 1; i < orderDetailService.bestSaler().size() ; i++) {
        }
        model.addAttribute("totalProduct", orderDetailService.bestSaler().size());
    }

}
