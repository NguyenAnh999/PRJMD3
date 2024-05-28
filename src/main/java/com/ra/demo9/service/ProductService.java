package com.ra.demo9.service;


import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.repository.IProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> getProduct(Integer currentPage,Integer size) {
        return productDao.getProduct(currentPage,size);
    }

    @Override
    public Product getProductById(Long pro_Id) {
        return productDao.getProductById(pro_Id);
    }

    @Override
    public boolean insertProduct(Product pro) {
        return productDao.insertProduct(pro);
    }

    @Override
    public boolean updateProduct(Product product,ProductRequest productRequest) {
        return productDao.updateProduct(product,productRequest);
    }

    @Override
    public boolean deleteProduct(Long pro_Id) {
        return productDao.deleteProduct(pro_Id);
    }

    @Override
    public String getImageByProductId(Long pro_id) {
        return productDao.getImageByProductId(pro_id);
    }

    @Override
    public List<Product> getProductByName(String name,Integer currentPage,Integer size) {
        return productDao.getProductByName(name,currentPage,size);
    }

    @Override
    public List<Product> sortByName(Integer currentPage,Integer size) {
        return productDao.sortByName(currentPage,size);
    }

    @Override
    public Long countAllProduct() {
        return productDao.countAllProduct();
    }

    @Override
    public Long countProductByName(String name) {
        return productDao.countProductByName(name);
    }
}
