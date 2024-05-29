package com.ra.demo9.controller;

import com.ra.demo9.model.entity.Voucher;
import com.ra.demo9.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vouchers")
public class VoucherController
{
    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public String getAllVouchers(Model model) {
        List<Voucher> vouchers = voucherService.getAllVouchers();
        model.addAttribute("vouchers", vouchers);
        return "vouchers";
    }

    @PostMapping("/add")
    public String addVoucher(@ModelAttribute Voucher voucher) {
        voucherService.addVoucher(voucher);
        return "redirect:/vouchers";
    }

    @PostMapping("/apply")
    public String applyVoucher(@RequestParam("code") String code, Model model) {
        Voucher voucher = voucherService.getVoucherByCode(code);
        if (voucher != null) {
            model.addAttribute("voucher", voucher);
            return "applyResult";
        } else {
            model.addAttribute("error", "Mã giảm giá không hợp lệ.");
            return "applyResult";
        }
    }
}
