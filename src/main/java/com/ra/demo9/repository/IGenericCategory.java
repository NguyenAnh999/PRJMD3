package com.ra.demo9.repository;



import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Category;


import java.util.List;

public interface IGenericCategory{
    List<Category> getCategory(Integer currentPage, Integer size);
    public Category getCategoryById(Long cat_Id);
    public boolean insertCategory(Category cat);
    public boolean updateCategory(Category cat);
    public boolean deleteCategory(Long cat_Id);

    public List<Category> getCategoryByName(String name,Integer currentPage,Integer size);
    public List<Category> sortByName(Integer currentPage,Integer size);

    public Long countAllCategory();
    public Long countCategoryByName(String name);
}
