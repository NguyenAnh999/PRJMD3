package com.ra.demo9.controller;

import com.ra.demo9.model.entity.Order;
import com.ra.demo9.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    OrderDao orderDao;
    @RequestMapping("/finish/{id}")
    public String finish(@PathVariable Long id, Model model) {
        Order order = orderDao.findById(id);
        order.setStatus("pendding");
        orderDao.update(order);
        model.addAttribute("oders",orderDao.findAll());
        return "adminoder";
    }
    @RequestMapping("/oderdetail/{id}")
    public String oderDetail(@PathVariable Long id, Model model) {
        Order order = orderDao.findById(id);
        model.addAttribute("oder",order);
      //  model.addAttribute("product",orderDao.)
        return "oderdetail";
    }

}
