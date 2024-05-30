package com.ra.demo9.controller;

import com.ra.demo9.model.entity.Category;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.ProductDetail;
import com.ra.demo9.service.CategoryService;
import com.ra.demo9.service.ProductDetailService;
import com.ra.demo9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping(value = "/Product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product findById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    @GetMapping(value = "/Category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Category findByIdCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    @GetMapping(value = "/ProductDetail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProductDetail findByIdProductDetail(@PathVariable Long id) {
        return productDetailService.getProductDetailById(id);
    }
}
