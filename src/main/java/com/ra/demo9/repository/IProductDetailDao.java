package com.ra.demo9.repository;

import com.ra.demo9.model.entity.ProductDetail;

import java.util.List;

public interface IProductDetailDao {
    List<ProductDetail> getProductDetail(Integer currentPage, Integer size);
    public ProductDetail getProductDetailById(Long proDetail_Id);
    public boolean insertProductDetail(ProductDetail proDetail);
    public boolean updateProductDetail(ProductDetail proDetail);
    public boolean deleteProductDetail(Long proDetail_Id);
    List<ProductDetail> findByProductId(Long productId);
    public List<ProductDetail> getProductDetailByName(String name,Integer currentPage,Integer size);
    public List<ProductDetail> sortByNameProductDetail(Integer currentPage,Integer size);

    public Long countAllProductDetail();
    public Long countProductDetailByName(String name);
}
