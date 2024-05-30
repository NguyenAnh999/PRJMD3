package com.ra.demo9.controller;

import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Category;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.service.FileService;
import com.ra.demo9.service.ICategoryService;
import com.ra.demo9.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @RequestMapping(value = {"/Category"})
    public String categoryHome(Model model, @RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "4") Integer size) {
        List<Category> categories = categoryService.getCategory(currentPage, size);
        model.addAttribute("category", new Category());
        model.addAttribute("iscategory", "category");
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) categoryService.countAllCategory() / size));
        model.addAttribute("categoryList", categories);
        return "/admincategory";
    }

    @GetMapping("/createCategory")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "/admincategory";
    }

    @PostMapping("/saveCategory")
    public String actionCreateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return "/admincategory";
        }
        categoryService.insertCategory(category);
        return "redirect:/admin/Category";
    }


    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/Category";
    }

    @GetMapping("/editCategory/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "/admincategory";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@Valid @ModelAttribute("category") Category category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/Category";
    }

    @GetMapping("/sortByNameCategory")
    public String sortByNameCategory(Model model, @RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "3") Integer size) {
        List<Category> categories = categoryService.sortByName(currentPage, size);
        model.addAttribute("issort", "sort");
        model.addAttribute("category", new Category());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) categoryService.countAllCategory() / size));
        model.addAttribute("categoryList", categories);
        return "/admincategory";
    }

    @GetMapping("/searchCategory")
    public String searchCategory(@RequestParam("search") String name, Model model, @RequestParam(defaultValue = "0") Integer currentPage, @RequestParam(defaultValue = "3") Integer size) {
        List<Category> searchCategory = categoryService.getCategoryByName(name, currentPage, size);
        model.addAttribute("category", new Category());
        model.addAttribute("issearch", name);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) categoryService.countCategoryByName(name) / size));
        model.addAttribute("categoryList", searchCategory);
        return "/admincategory";
    }
}
