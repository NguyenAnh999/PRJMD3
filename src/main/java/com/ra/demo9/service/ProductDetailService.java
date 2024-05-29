package com.ra.demo9.service;

import com.ra.demo9.model.entity.ProductDetail;
import com.ra.demo9.repository.IProductDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductDetailService implements IProductDetailService{
    @Autowired
    private IProductDetailDao productDetailDao;
    @Override
    public List<ProductDetail> getProductDetail(Integer currentPage, Integer size) {
        return productDetailDao.getProductDetail(currentPage,size);
    }

    @Override
    public ProductDetail getProductDetailById(Long proDetail_Id) {
        return productDetailDao.getProductDetailById(proDetail_Id);
    }

    @Override
    public boolean insertProductDetail(ProductDetail proDetail) {
        return productDetailDao.insertProductDetail(proDetail);
    }

    @Override
    public boolean updateProductDetail(ProductDetail proDetail) {
        return productDetailDao.updateProductDetail(proDetail);
    }

    @Override
    public boolean deleteProductDetail(Long proDetail_Id) {
        return productDetailDao.deleteProductDetail(proDetail_Id);
    }

    @Override
    public List<ProductDetail> findByProductId(Long productId) {
        return productDetailDao.findByProductId(productId);
    }

    @Override
    public List<ProductDetail> getProductDetailByName(String name, Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public List<ProductDetail> sortByNameProductDetail(Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public Long countAllProductDetail() {
        return productDetailDao.countAllProductDetail();
    }

    @Override
    public Long countProductDetailByName(String name) {
        return null;
    }
}
