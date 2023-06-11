package com.example.demo.Controller;

import com.example.demo.Entity.orderEntity;
import com.example.demo.Repository.OrderDetailRepository;
import com.example.demo.Service.ServiceImpl.OderServiceImpl;
import com.example.demo.Service.ServiceImpl.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BillAdminController {
    @Autowired
    OderServiceImpl ordOderService;
    @Autowired
    OrderDetailRepository orderDetailService;

    @GetMapping("/bill")
    public String showBill(Model model) {
        model.addAttribute("bill", ordOderService.finByAll(0));
        return "/Admin/bill";
    }

    @GetMapping("/bill/oke")
    public String showBillOke(Model model) {
        model.addAttribute("bill", ordOderService.finByAll(1));
        return "/Admin/billOke";
    }
    @GetMapping("/bill/da-huy")
    public String showBillHuy(Model model) {
        model.addAttribute("bill", ordOderService.finByAll(2));
        return "/Admin/billOke";
    }

    @GetMapping("/bill/confirm-bill/{id}")
    public String confirmBill(@PathVariable int id) {
        orderEntity orderEntity = ordOderService.finById(id);
        orderEntity.setStatus(1);
        ordOderService.saveOrder(orderEntity);
        return "redirect:/admin/bill";
    }

    @GetMapping("/bill/cannel-bill/{id}")
    public String cannelBill(@PathVariable int id) {
        orderEntity orderEntity = ordOderService.finById(id);
        orderEntity.setStatus(2);
        ordOderService.saveOrder(orderEntity);
        return "redirect:/admin/bill";
    }
}
