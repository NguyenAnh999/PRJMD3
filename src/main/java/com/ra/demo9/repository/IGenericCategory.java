package com.ra.demo9.repository;



import com.ra.demo9.model.entity.Category;

import java.util.List;

public interface IGenericCategory{
    List<Category> getCategory();
    public Category getCategoryById(Long cat_Id);
    public boolean insertCategory(Category cat);
    public boolean updateCategory(Category cat);
    public boolean deleteCategory(Long cat_Id);
    public List<Category> getCategoryByName(String name);
}
