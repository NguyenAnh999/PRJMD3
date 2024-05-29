package com.ra.demo9.controller;

import com.ra.demo9.model.dto.UsersDTO;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
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
    public String UpdateAfter(Model model, @ModelAttribute("user") UsersDTO user, @RequestParam ("id") Long id,HttpSession session) {
        userService.update(user,false,id);
        session.setAttribute("user",userService.findById(id));
        return "userinfo";
    }
}
