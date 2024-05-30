package com.ra.demo9.controller;

import com.ra.demo9.model.dto.UsersDTO;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.repository.AddressDao;
import com.ra.demo9.service.AdminService;
import com.ra.demo9.service.ProductService;
import com.ra.demo9.service.ShoppingCartService;
import com.ra.demo9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
 //   @Autowired
//    private Facebook facebook;

//    @Autowired
//    private ConnectionRepository connectionRepository;
    @Autowired
    AdminService adminService;
    @Autowired
    AddressDao addressDao;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/adminlogin")
    public String adminLogin() {
        return "Adminlogin";
    }

    @PostMapping("/admincheck")
    public String adminCheck(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Users users = adminService.getUser(username, password);
        if (users!=null) {
            session.setAttribute("user", users);
            session.setAttribute("address",addressDao.getAllAddress(users.getUserId()));
            if (users.getRoleList().stream().anyMatch(role -> role.getRoleId()==1)){
            return "admin";}
            else {
                return "index";
            }
        } else {
            return "Adminlogin";
        }
    }
    @RequestMapping("/signupadmin")
    public String login(Model model) {
        model.addAttribute("user", new UsersDTO());
        return "dangkyadmin";
    }

//
//    @GetMapping("/facebookLogin")
//    public String facebookLogin(Model model) {
//        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//            return "redirect:/connect/facebook";
//        }
//        model.addAttribute("userProfile", facebook.userOperations().getUserProfile());
//        return "profile";
//    }
@Autowired
private UserService userService;

@Autowired
private ProductService productService;
@Autowired
private ShoppingCartService shoppingCartService;
    @RequestMapping("/signup")
    public String addAdmin(Model model) {
        model.addAttribute("user", new UsersDTO());
        return "dangky";
    }
    @PostMapping("/signupAfter")
    public String addAdminAfter(@Valid @ModelAttribute(name = "user") UsersDTO user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user",user);
            return "dangky";
        }
        model.addAttribute("user", "isUser");
        userService.save(user,false);
        return "Dkythanhcong";
    }

    @RequestMapping("/loginUser")
    public String loginUser() {
        return "login";
    }


    @RequestMapping("/productList")
    public String productHomeUser(Model model,@RequestParam (defaultValue = "0") int currentPage,@RequestParam(defaultValue = "8") int size) {
        List<Product> products = productService.getProduct(currentPage,size);
        model.addAttribute("totalMoney" ,shoppingCartService.getShoppingCartTotal());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", Math.ceil((double) productService.countAllProduct()/size));
        model.addAttribute("products", products);
        // model.addAttribute("categories", categoryService.getCategory());
        return "Product";

    }

}