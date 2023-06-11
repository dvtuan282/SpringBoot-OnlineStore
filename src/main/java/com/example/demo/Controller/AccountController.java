package com.example.demo.Controller;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Entity.CartEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.ServiceImpl.AccountServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    AccountServiceImpl service;

    @GetMapping("/admin/account")
    public String showAccount(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber, Model model) {
        int pageSize = 5;
        Page<AccountEntity> page = service.finAllAccount(pageNumber - 1, pageSize);
        List<AccountEntity> account = page.getContent();
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("pageNo", pageNumber);
        model.addAttribute("account", account);
        return "/Admin/account";
    }

    //    delete account
    @GetMapping("/admin/account/delete/{id}")
    public String deleteAcount(@PathVariable int id) {
        AccountEntity accountEntity = service.findById(id);
        service.deleteAccount(accountEntity);
        return "redirect:/admin/acount";
    }

    //  Admin đăng ký trả về trang login với url là adminLogin
    @GetMapping("/admin/register")
    public String formRegister(Model model) {
        model.addAttribute("account", new AccountEntity());
        model.addAttribute("acction", "/admin/register");
        return "/Admin/registerAdmin";
    }

    @PostMapping("/admin/register")
    public String registerAccount(@Valid @ModelAttribute("account") AccountEntity accountEntity,
                                  BindingResult result,
                                  Model model
    ) {
        if (result.hasErrors()) {
            return "/Admin/registerAdmin";
        }
        accountEntity.setRole(0);
        service.saveAccount(accountEntity);
        return "redirect:/admin/login";
    }

    // Người dùng đăng ký trả về trang login với url là userLogin
    @GetMapping("/user/register")
    public String userRegister(Model model) {
        model.addAttribute("account", new AccountEntity());
        model.addAttribute("acction", "/user/register");
        return "/Admin/registerAdmin";
    }

    @PostMapping("/user/register")
    public String userRegisterAccount(@Valid @ModelAttribute("account") AccountEntity accountEntity,
                                      BindingResult result,
                                      Model model
    ) {
        if (result.hasErrors()) {
            return "/Admin/registerAdmin";
        }
        accountEntity.setRole(1);
        service.saveAccount(accountEntity);
        return "redirect:/user/login";
    }

}
