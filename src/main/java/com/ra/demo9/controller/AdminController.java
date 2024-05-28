package com.ra.demo9.controller;

import com.ra.demo9.model.dto.UsersDTO;
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
    public String addAdminAfter(@ModelAttribute(name = "user") UsersDTO user, HttpSession session) {
          userService.save(user,true);
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
}
