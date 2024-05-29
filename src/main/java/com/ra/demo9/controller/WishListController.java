package com.ra.demo9.controller;

import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.WishList;
import com.ra.demo9.service.IProductService;
import com.ra.demo9.service.ShoppingCartService;
import com.ra.demo9.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    private IProductService productService;
    @RequestMapping("/myWishList")
    public String wishList(Model model) {
        Long userId = 1L;
        List<Product> productWishList = wishListService.getAllWishList(userId);
        model.addAttribute("productWishList", productWishList);
        return "LikeProduct";
    }

    @GetMapping("/deleteWishList/{id}")
    public String deleteWishList(@PathVariable("id") Integer id )
    {
      wishListService.deleteWishList(id);
        return "redirect:/myWishList";
    }
    @GetMapping("/addWishList/{id}")
    public String addWishList(@PathVariable("id") Long productId, Model model){
        Long userId = 1L;
        WishList wishList = new WishList();
        wishList.setProductId(productId);
        wishList.setUserId(userId);

        wishListService.addWishList(wishList);
        return "redirect:/myWishList";
    }
}
