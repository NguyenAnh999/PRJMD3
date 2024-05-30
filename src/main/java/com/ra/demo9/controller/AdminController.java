package com.ra.demo9.controller;

import com.ra.demo9.model.dto.UsersDTO;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.repository.OrderDao;
import com.ra.demo9.repository.impl.DashBroadDao;
import com.ra.demo9.service.AdminService;
import com.ra.demo9.service.ProductService;
import com.ra.demo9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DashBroadDao dashBroadDao;

@RequestMapping("/")
public String index(Model model) {
    model.addAttribute("AllProducts",dashBroadDao.getAllQuantityProduct() );
    model.addAttribute("allSaleProduct",dashBroadDao.getAllQuantitySale());
    model.addAttribute("allMoney",dashBroadDao.getAllPriceSale());
    model.addAttribute("allUser",dashBroadDao.getCountAllUser());
    return "admin";
}
    @RequestMapping("/logoutadmin")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Adminlogin";
    }
    @RequestMapping("/user")
    public String user(Model model,@RequestParam(defaultValue = "0") int currentPage,@RequestParam(defaultValue = "2") int size) {
        model.addAttribute("isuser" ,"user");
        model.addAttribute("current",currentPage);
        model.addAttribute("totalPages",Math.ceil( (double) userService.getCountUser() / size));
        model.addAttribute("userList", userService.findAll(currentPage,size));

        return "adminuser";
    }
    @RequestMapping("/addadmin")
    public String addAdmin(Model model) {
        model.addAttribute("user", new UsersDTO());
        return "addadmin";
    }
    @PostMapping("/addaffter")
    public String addAdminAfter(@ModelAttribute(name = "user") UsersDTO user, Model model) {
          userService.save(user,true);
        model.addAttribute("admin", "isAdmin");

          return "Dkythanhcong";
    }
    @RequestMapping("/blockacc/{id}")
    public String block(@PathVariable (name = "id") Long id) {
        userService.updateStatus(userService.findById(id));
        return "redirect:/admin/user";
    }
    @RequestMapping("/search")
    public String search(@RequestParam(name = "username") String username,Model model,@RequestParam(defaultValue = "0") int currentPage,@RequestParam(defaultValue = "2") int size) {
        model.addAttribute("issearch" ,username);
        model.addAttribute("current",currentPage);
        model.addAttribute("totalPages",Math.ceil( (double) userService.getCountUserByName(username) / size));
        model.addAttribute("userList", userService.findByName(username,currentPage,size));
        return "adminuser";
    }
    @RequestMapping("/sort")
    public String sort(Model model,@RequestParam(defaultValue = "0") int currentPage,@RequestParam(defaultValue = "2") int size) {
        model.addAttribute("issort" ,"sort");
        model.addAttribute("current",currentPage);
        model.addAttribute("totalPages",Math.ceil( (double) userService.getCountUser() / size));
        model.addAttribute("userList", userService.findOderByFullName(currentPage,size));
        return "adminuser";
    }
    @RequestMapping("/oder")
    public String oder(Model model) {
        model.addAttribute("oders",orderDao.findAll());
    return "adminoder";
    }
}
