package com.ra.demo9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
    @RequestMapping("/")
    public String shoppingCart() {
        return "giohang";
    }
}
