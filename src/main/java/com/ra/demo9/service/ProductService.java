package com.ra.demo9.service;

import com.ra.demo9.model.entity.Product;
import com.ra.demo9.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;
public List<Product> getProducts() {
    return productDao.findAll();
}
}
