package com.ra.demo9.service;


import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.ProductDetail;
import com.ra.demo9.repository.IProductDao;
import com.ra.demo9.repository.impl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductDao productDao;
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private ProductImpl productImpl;

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
    public boolean updateProductStock(Product pro) {
        return productDao.updateProductStock(pro);
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
    public List<Product> listProductOfCategory(Long category_id, String name) {
        return productDao.listProductOfCategory(category_id,name);
    }

    @Override
    public Long countAllProduct() {
        return productDao.countAllProduct();
    }


    @Override
    public Long countProductByName(String name) {
        return productDao.countProductByName(name);
    }

    public Integer getTotalQuantityByProductId(Long productId) {
        List<ProductDetail> productDetails = productDetailService.findByProductId(productId);
        int totalQuantity = 0;
        for (ProductDetail pd : productDetails) {
            totalQuantity += pd.getQuantity();
        }
        return totalQuantity;
    }
    public void minusePr(Product product){
        product.setStockQuantity(product.getStockQuantity()-1);
        productImpl.minusePr(product);
    }
}
