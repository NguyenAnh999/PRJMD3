package com.ra.demo9.controller;

import com.ra.demo9.model.entity.Address;
import com.ra.demo9.model.entity.ShoppingCart;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @RequestMapping("/myCart")
    public String shoppingCart(Model model, @SessionAttribute ("user") Users user) {
        model.addAttribute("shoppingCart", shoppingCartService.getShoppingCart(user.getUserId()));
        model.addAttribute("totalPro", shoppingCartService.totalPro(user.getUserId()));
        model.addAttribute("money", shoppingCartService.getShoppingCartTotal(user.getUserId()));
        return "shoppingCart";
    }

    @GetMapping("/deleteAllShopCart")
    public String deleteAllShopCart(@SessionAttribute("user") Users user)
    {
        shoppingCartService.deleteAllShopCart(user.getUserId());
        return "redirect:/myCart";
    }
    @GetMapping("/deleteItemShoppingCart/{id}")
    public String deleteItemShoppingCart(@PathVariable ("id") Integer id ){
        shoppingCartService.deleteItemShoppingCart(id);
        return "redirect:/myCart";
    }
    @PostMapping("/payall")
    public String payAll(@SessionAttribute("user") Users user, @SessionAttribute ("address") List<Address> address, Model model, @RequestParam ("choiceAddress") Integer addressId) {
        shoppingCartService.cartToOrder(user,address.get(addressId),shoppingCartService.getShoppingCartTotal(user.getUserId()),shoppingCartService.getShoppingProduct(user.getUserId()));

       shoppingCartService.deleteAllShopCart(user.getUserId());
        return "index";
    }

}
