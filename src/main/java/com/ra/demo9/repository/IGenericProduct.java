package com.ra.demo9.repository;


import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Product;

import java.util.List;

public interface IGenericProduct {
    List<Product> getProduct(int currentPage,int size);
    public Product getProductById(Long pro_Id);
    public boolean insertProduct(Product pro);
    public boolean updateProduct(Product pro, ProductRequest productRequest);
    public boolean deleteProduct(Long pro_Id);
    String getImageByProductId(Long pro_id);
    public List<Product> getProductByName(String name,int currentPage,int size);
    public List<Product> sortByName(int currentPage,int size);
    public List<Product> listProductOfCategory(int category_id,String name);
    public Long countAllProduct();

}
