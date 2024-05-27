package com.ra.demo9.controller;

import com.ra.demo9.model.entity.Product;
import com.ra.demo9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/Product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product findById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
