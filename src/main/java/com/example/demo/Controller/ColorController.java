package com.example.demo.Controller;

import com.example.demo.Entity.ColorEntity;
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.ColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/admin/color")
public class ColorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/list")
    public String showListColor(Model model) {
        return pageColor(model, 1);
    }

    @GetMapping("/list/{pageNo}")
    public String pageColor(Model model, @PathVariable int pageNo) {
        Page<ColorEntity> page = colorService.pageColor(pageNo - 1, 5);
        List<ColorEntity> listColor = page.getContent();

        model.addAttribute("listColor", listColor);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("action", "/admin/color/add");
        model.addAttribute("color", new ColorEntity());
        return "/Admin/color";
    }

    @GetMapping("/delete/{id}")
    public String deleteColor(@PathVariable(value = "id") int id) {
        ColorEntity colorEntity = colorService.getCollorById(id);
        colorService.deleteColor(colorEntity);
        return "redirect:/admin/color/list";
    }

    @PostMapping("/add")
    public String addColor(@ModelAttribute("color") ColorEntity colorEntity) {
        colorService.saveColor(colorEntity);
        return "redirect:/admin/color/list";
    }

    @GetMapping("/edit")
    public String editColor(@RequestParam int id, Model model) {
        model.addAttribute("color", colorService.getCollorById(id));
        model.addAttribute("action", "/admin/color/update?id=" + id);
        return "/Admin/editColor";
    }

    @PostMapping("/update")
    public String updateColor(@Valid @ModelAttribute("color") ColorEntity colorEntity, BindingResult result, @RequestParam int id, Model model) {
        if (result.hasErrors()) {
            return "/Admin/editColor";
        }
        colorEntity.setId(id);
        colorService.saveColor(colorEntity);
        return pageColor(model, 1);

    }

}
