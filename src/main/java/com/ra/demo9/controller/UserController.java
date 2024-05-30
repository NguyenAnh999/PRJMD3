package com.ra.demo9.controller;

import com.ra.demo9.model.dto.UsersDTO;
import com.ra.demo9.model.entity.Address;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.repository.AddressDao;
import com.ra.demo9.service.ProductService;
import com.ra.demo9.service.ShoppingCartService;
import com.ra.demo9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService
            shoppingCartService;
    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    public String info() {
        return "userinfo";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @RequestMapping("/changeInfo")
    public String changeInfo(Model model) {
        model.addAttribute("user", new UsersDTO());
        return "changeInfo";
    }

    @RequestMapping("/UpdateAfter")
    public String UpdateAfter(Model model, @ModelAttribute("user") UsersDTO user, @RequestParam("id") Long id, HttpSession session) {
        userService.update(user, false, id);
        session.setAttribute("user", userService.findById(id));
        return "userinfo";
    }

    @RequestMapping("/addProductToWishList/{id}")
    public String addProductToWishList(@PathVariable("id") Long productId, Model model, HttpSession session, @RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "8") int size,@SessionAttribute ("user") Users user) {
        Product product = productService.getProductById(productId);
        shoppingCartService.addToCart(product, user);
        List<Product> products = productService.getProduct(currentPage, size,false);

        model.addAttribute("totalMoney", shoppingCartService.getShoppingCartTotal(user.getUserId()));

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct() / size));
        model.addAttribute("products", products);
        // model.addAttribute("categories", categoryService.getCategory());
        return "Product";
    }

    @RequestMapping("/addProductToCart/{id}")
    public String addProductToCart(@PathVariable("id") Long productId, Model model, @RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "8") int size,@SessionAttribute ("user") Users user) {
        Product product = productService.getProductById(productId);
        shoppingCartService.addToCart(product,user );
        List<Product> products = productService.getProduct(currentPage, size,false);
        model.addAttribute("totalMoney", shoppingCartService.getShoppingCartTotal(user.getUserId()));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct() / size));
        model.addAttribute("products", products);
        // model.addAttribute("categories", categoryService.getCategory());
        return "Product";
    }

    @Autowired
    AddressDao addressDao;

    @RequestMapping("/add/address")
    public String addAddress(Model model) {
        model.addAttribute("address", new Address());
        return "addAddress";
    }

    @RequestMapping("/add/address/after")
    public String add(@ModelAttribute("address") Address address,HttpSession session,@SessionAttribute ("user") Users users) {
        address.setUsers(users);
        addressDao.addAddress(address);
        session.setAttribute("address",addressDao.getAllAddress(users.getUserId()));
        return "userinfo";
    }
}
