package com.ra.demo9.service;

import com.ra.demo9.model.entity.Voucher;
import com.ra.demo9.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService
{
    @Autowired
    private VoucherRepository voucherRepository;

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public Voucher getVoucherByCode(String code) {
        return voucherRepository.findByCode(code);
    }

    public void addVoucher(Voucher voucher) {
        voucherRepository.save(voucher);
    }
}
