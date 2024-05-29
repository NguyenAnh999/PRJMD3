package com.ra.demo9.controller;

import com.ra.demo9.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @RequestMapping("/myCart")
    public String shoppingCart(Model model) {
        model.addAttribute("shoppingCart", shoppingCartService.getShoppingCart());
        return "shoppingCart";
    }

    @GetMapping("/deleteAllShopCart")
    public String deleteAllShopCart()
    {
        shoppingCartService.deleteAllShopCart();
        return "redirect:/myCart";
    }
    @GetMapping("/deleteItemShoppingCart/{id}")
    public String deleteItemShoppingCart(@PathVariable ("id") Integer id ){
        shoppingCartService.deleteItemShoppingCart(id);
        return "redirect:/myCart";
    }
}
