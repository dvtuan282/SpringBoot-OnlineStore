package com.example.demo.Controller;

import com.example.demo.Entity.SizeEntity;
import com.example.demo.Service.ServiceImpl.SizeServiceImpl;
import com.example.demo.Service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SizeController {
    @Autowired
    SizeServiceImpl service;

    @GetMapping("/size/list")
    public String showListSize(Model model) {

        return showListSize(model, 1);
    }

    @GetMapping("/size/list/{pageNo}")
    public String showListSize(Model model, @PathVariable int pageNo) {
        Page<SizeEntity> page = service.pageSize(pageNo - 1, 5);
        List<SizeEntity> listSize = page.getContent();
        model.addAttribute("listSize", listSize);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("size", new SizeEntity());
        model.addAttribute("action", "/admin/size/save");
        return "/Admin/size";
    }

    @PostMapping("/size/save")
    public String saveSize(@Valid @ModelAttribute("size") SizeEntity sizeEntity, BindingResult result) {
        if (result.hasErrors()) {
            return "/Admin/size";
        }
        service.saveSize(sizeEntity);
        return "redirect:/admin/size/list";
    }

    @GetMapping("/size/delete/{id}")
    public String deleteSize(@PathVariable(value = "id") int id) {
        SizeEntity sizeEntity = service.getSizeById(id);
        service.deleteSize(sizeEntity);
        return "redirect:/admin/size/list";
    }

    @GetMapping("/size-edit/{id}")
    public String editSize(@PathVariable int id, Model model) {
        SizeEntity size = service.getSizeById(id);
        model.addAttribute("size", size);
        model.addAttribute("action", "/admin/size/update/" + id);
        return "/Admin/editSize";
    }

    @PostMapping("/size/update/{id}")
    public String updateSize(@Valid @ModelAttribute("size") SizeEntity sizeEntity, BindingResult result,
                             @PathVariable int id, Model model) {
        if (result.hasErrors()) {
            return "/Admin/editSize";
        }
        sizeEntity.setId(id);
        service.saveSize(sizeEntity);
        return showListSize(model, 1);
    }
}
