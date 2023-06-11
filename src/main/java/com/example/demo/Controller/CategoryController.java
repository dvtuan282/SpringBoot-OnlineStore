package com.example.demo.Controller;

import com.example.demo.Entity.CategoryEntity;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ServiceImpl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/category/list")
    public String showListCategory(Model model) {
        return pageCategory(model, 1);
    }

    @GetMapping("/category/list/{pageNo}")
    public String pageCategory(Model model, @PathVariable int pageNo) {
        int pageSize = 5;
        Page<CategoryEntity> page = categoryService.pageCategory(pageNo - 1, pageSize);
        List<CategoryEntity> listCategory = page.getContent();
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("action", "/admin/category/save");
        return "/Admin/category";
    }

    @PostMapping("/category/save")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryEntity categoryEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return pageCategory(model,1);

        } else {
            categoryService.saveCategory(categoryEntity);
        }
//        return "redirect:/admin/category/list";
        return showListCategory(model);
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable(value = "id") int id) {
        CategoryEntity categoryEntity = categoryService.getByIdCategory(id);
        categoryService.deleteCategory(categoryEntity);
        return "redirect:/admin/category/list";
    }

    @GetMapping("/category-edit/{id}")
    public String editCategory(@PathVariable int id, Model model) {
        CategoryEntity categoryEntity = categoryService.getByIdCategory(id);
        model.addAttribute("category", categoryEntity);
        model.addAttribute("action", "/admin/category/update/" + id);
        return "/Admin/editCategory";
    }

    @PostMapping("/category/update/{id}")
    public String updateCategory(@Valid @ModelAttribute("category") CategoryEntity categoryEntity, BindingResult result, @PathVariable int id, Model model) {
        if (result.hasErrors()) {
            return "/Admin/editCategory";
        }
        categoryEntity.setId(id);
        categoryService.saveCategory(categoryEntity);
        return pageCategory(model, 1);
    }
}
