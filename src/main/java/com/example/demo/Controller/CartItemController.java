package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Service.ServiceImpl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class CartItemController {
    Date date = new Date();

    // Định dạng ngày giờ
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    ProductDetailServiceImpl repository;
    @Autowired
    OderServiceImpl oderService;
    @Autowired
    ColorServiceImpl colorService;
    @Autowired
    SizeServiceImpl sizeService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductDetailServiceImpl productDetailService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    OrderDetailServiceImpl orderDetailService;


    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {
        if (session.getAttribute("name") == null) {
            return "redirect:/user/login";
        }
        subTotal(account(session), model);
        model.addAttribute("item", cartService.findAll(account(session)));
        return "shoping-cart";
    }

    @PostMapping("/addItems")
    public String addCartItems(
            @RequestParam("product") int product,
            @RequestParam("size") int size,
            @RequestParam("color") int color,
            @RequestParam("quantity") int quantity,
            Model model,
            HttpSession session
    ) {
        if (session.getAttribute("name") == null) {
            return "redirect:/user/login";
        }

        ColorEntity colorEntity = colorService.getCollorById(color);
        SizeEntity sizeEntity = sizeService.getSizeById(size);
        ProductEntity productEntity = productService.getProductById(product);
        Double totalPrice = (productEntity.getPrice() * quantity);
        ProductDetailEntity productDetailEntity = repository.findByProductAndColorAndSize(productEntity, colorEntity, sizeEntity);
        String userName = (String) session.getAttribute("name");
        AccountEntity accountEntity = accountService.findByUser(userName);
        Optional<CartEntity> cartEntityOptional = cartService.finByAccountAndProduct(accountEntity, productDetailEntity);
        if (cartEntityOptional.isPresent()) {
//        update quantity
            CartEntity cartEntity = cartEntityOptional.get();
            cartEntity.setQuantity(cartEntity.getQuantity() + quantity);
            Double totalPriceUp = (productEntity.getPrice() * cartEntity.getQuantity());
            cartEntity.setTotalPrice(totalPriceUp);
            cartService.addCart(cartEntity);
        } else {
//                save new
            CartEntity cartNew = new CartEntity(0, accountEntity, productDetailEntity, quantity, totalPrice);
            cartService.addCart(cartNew);
        }
        subTotal(accountEntity, model);
        return "redirect:/user/cart";
    }

    @GetMapping("/delete-cartItem/{id}")
    public String deleteCartItem(@PathVariable int id
    ) {
        CartEntity cartEntity = cartService.getById(id);
        cartService.deleteCartItem(cartEntity);
        return "redirect:/user/cart";
    }

    @GetMapping("/update/{id}/quantity")
    public String updateQuantity(@PathVariable("id") int id,
                                 @RequestParam("quantity") int quantity) {

        CartEntity cartEntity = cartService.getById(id);
        cartEntity.setQuantity(quantity);
        cartEntity.setTotalPrice(cartEntity.getQuantity() * cartEntity.getProductDetailEntity().getProduct().getPrice());
        cartService.addCart(cartEntity);
        if (cartEntity.getQuantity() == 0) {
            deleteCartItem(id);
        }
        return "redirect:/user/cart";
    }

    @PostMapping("/check-out")
    public String checkOut(HttpSession session,
                           @RequestParam("phoneNumber") String numberPhone,
                           @RequestParam("city") String city,
                           @RequestParam("address") String address

    ) {
        orderEntity orderEntity = null;
        orderEntity = creatOder(numberPhone, city, address, session);
        for (CartEntity cart : cartService.findAll(account(session))
        ) {
            ProductDetailEntity product = cart.getProductDetailEntity();
            int quantity = cart.getQuantity();
            double amnount = cart.getTotalPrice();
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity(0, orderEntity, product, quantity, amnount);
            orderDetailService.save(orderDetailEntity);
            cartService.deleteCartItem(cart);
//            update sl sp
            product.setQuantity(product.getQuantity() - quantity);
            productDetailService.saveProductD(product);
        }
        return "redirect:/user/cart";
    }

    public void subTotal(AccountEntity accountEntity, Model model) {
        int subTotal = 0;
        for (CartEntity cart : cartService.findAll(accountEntity)
        ) {
            subTotal += cart.getTotalPrice();
        }
        model.addAttribute("subTotal", subTotal);
    }

    public orderEntity creatOder(String phoneNumber, String city, String address, HttpSession session) {
//       Address
        String addressD = city.concat(" ").concat(address);
//      account1
        orderEntity orderEntity = new orderEntity(0, account(session), date, phoneNumber, address, 0);
        oderService.saveOrder(orderEntity);
        return orderEntity;
    }

    public AccountEntity account(HttpSession session) {
        String userName = (String) session.getAttribute("name");
        AccountEntity accountEntity = accountService.findByUser(userName);
        return accountEntity;
    }


}
