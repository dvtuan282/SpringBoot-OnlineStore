package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Service.ServiceImpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class BillUserController {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    OderServiceImpl oderService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    ProductDetailServiceImpl productService;
    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @GetMapping("/my-purchase")
    public String purchase(Model model, HttpSession session) {

        model.addAttribute("bill", oderService.findByAccountAndStatus(findAccount(session), 1));
        return "bill";
    }

    @GetMapping("/my-purchases")
    public String purchases(Model model, HttpSession session) {
        String name = (String) session.getAttribute("name");
        AccountEntity account = accountService.findByUser(name);
        model.addAttribute("bill", oderService.findByAccountAndStatus(account, 2));
        return "bill";
    }
    @GetMapping("/cho-xac-nhan")
    public String confirm(Model model, HttpSession session) {
        String name = (String) session.getAttribute("name");
        AccountEntity account = accountService.findByUser(name);
        model.addAttribute("bill", oderService.findByAccountAndStatus(account, 0));
        return "bill2";
    }

    @GetMapping("/bill/huy-don-hang/{id}")
        public String cannelBill(@PathVariable int id) {
            orderEntity orderEntity = oderService.finById(id);
            orderEntity.setStatus(2);
            oderService.saveOrder(orderEntity);
            return "redirect:/user/cho-xac-nhan";
        }
    @PostMapping("/buy-again/{id}")
    public String buyAgain(@PathVariable int id,
                           HttpSession session) {
        orderEntity orderEntity = oderService.finById(id);

//        System.out.println("id của háo đơn+ " + id);

        for (OrderDetailEntity odd : orderDetailService.findByOrder(orderEntity)
        ) {
            ProductDetailEntity product = productService.getProductDById(odd.getProductDetailEntity().getId());
//            System.out.println("id chi tiết sản phẩm: " +  product.getId());
//            System.out.println("id của odd:"+odd.getId());
            CartEntity cart = new CartEntity(0,findAccount(session),product,odd.getQuantity(),odd.getAmount());
            System.out.println("cart+++" + cart);
            cartService.addCart(cart);
        }
        return "redirect:/user/cart";
    }

    public AccountEntity findAccount(HttpSession session){
        String name = (String) session.getAttribute("name");
        AccountEntity account = accountService.findByUser(name);
        return account;
    }
}

