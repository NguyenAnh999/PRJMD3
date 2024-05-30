package com.ra.demo9.controller;

import com.ra.demo9.model.entity.Comment;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.ProductDetail;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.service.CommentService;
import com.ra.demo9.service.IProductDetailService;
import com.ra.demo9.service.ProductService;
import com.ra.demo9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/user")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;
    @Autowired
    private IProductDetailService productDetailService;

    @Autowired
    private UserService userService;

    @RequestMapping("/addComment")
    public String addComment(@ModelAttribute("comment") Comment comment,
                             @SessionAttribute("user") Users users,@RequestParam("productId") Long id) {
        comment.setUser(users);
        comment.setProduct(productService.getProductById(id));
        commentService.addComment(comment);
        return "redirect:/user/comment/"+id;
    }

    @RequestMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model,@SessionAttribute("user") Users users) {
        Product product = productService.getProductById(id);
        List<Comment> commentList = commentService.getAllComment(users.getUserId());
        List<ProductDetail> productDetails = productDetailService.findByProductId(id);
        List<Product> products = productService.listProductOfCategory(id, product.getProductName());
        model.addAttribute("productDel", product);
        model.addAttribute("product", product);
        model.addAttribute("commentList", commentList);
        model.addAttribute("comment",new Comment());
        model.addAttribute("productDetailList2", productDetails);
        model.addAttribute("productList5", products);
        return "Productdetail";
    }
}
