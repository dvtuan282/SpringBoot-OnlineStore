package com.example.demo.Controller;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Service.ServiceImpl.AccountServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AuthenController {
    @Autowired
    AccountServiceImpl service;

    @GetMapping("/admin/login")
    public String adminLogin(Model model) {
        model.addAttribute("account", new AccountEntity());
        model.addAttribute("acction", "/admin/register");
        return "/Admin/login";
    }

    @GetMapping("/user/login")
    public String userLogin(Model model) {
        model.addAttribute("account", new AccountEntity());
        model.addAttribute("acction", "/user/register");
        return "/Admin/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("account") AccountEntity accountEntity,
                        BindingResult result,
                        HttpSession session,
                        Model model) {
        AccountEntity accountDB = null;
        accountDB = service.findByUserAndPass(accountEntity.getUserName(), accountEntity.getPassWord());
        System.out.println(accountDB);
        if (result.hasErrors()) {
            return "/Admin/login";
        }
        if (accountDB == null) {
            model.addAttribute("mess", "Account does not exist!!");
            return "/Admin/login";
        }
        if (accountDB.getRole() == 0) {
            session.setAttribute("name", accountDB.getUserName());
            return "redirect:/admin/home";
        }
        session.setAttribute("name", accountDB.getUserName());
        return "redirect:/user/product";
    }

}
