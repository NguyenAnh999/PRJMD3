package com.ra.demo9.controller;

import com.ra.demo9.model.entity.*;
import com.ra.demo9.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/user")
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @RequestMapping("/myWishList")
    public String wishList(Model model, @SessionAttribute("user") Users users) {
        List<Product> productWishList = wishListService.getAllWishList(users.getUserId());
        model.addAttribute("productWishList", productWishList);
        return "LikeProduct";
    }

    @GetMapping("/deleteWishList/{id}")
    public String deleteWishList(@PathVariable("id") Long id, @SessionAttribute("user") Users users) {
        wishListService.deleteWishList(users.getUserId(),id);
        return "redirect:/user/myWishList";
    }

    @GetMapping("/addWishList/{id}")
    public String addWishList(@PathVariable("id") Long productId, @SessionAttribute("user") Users users) {
        Product product = productService.getProductById(productId);
        Users user = userService.findById(users.getUserId());
        WishList wishList = new WishList();
        wishList.setProduct(product);
        wishList.setUser(user);

        wishListService.addWishList(wishList);
        return "redirect:/user/myWishList";
    }

    @RequestMapping("/showProductDetail/{id}")
    public String showProductDetail(@PathVariable("id") Long id, Model model,@SessionAttribute("user") Users users) {
        Product product = productService.getProductById(id);
        List<ProductDetail> productDetails = productDetailService.findByProductId(id);
        List<Product> products = productService.listProductOfCategory(id, product.getProductName());
        List<Comment> commentList = commentService.getAllComment(users.getUserId());
        model.addAttribute("productDel", product);
        model.addAttribute("productDetailList2", productDetails);
        model.addAttribute("productList5", products);
        model.addAttribute("product", product);
        model.addAttribute("commentList", commentList);
        model.addAttribute("comment",new Comment());
        return "/Productdetail";
    }
}
