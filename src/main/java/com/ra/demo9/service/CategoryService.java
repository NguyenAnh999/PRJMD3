package com.ra.demo9.service;

import com.ra.demo9.model.entity.Category;
import com.ra.demo9.repository.ICategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<Category> getCategory(Integer currentPage, Integer size) {
        return categoryDao.getCategory(currentPage,size);
    }

    @Override
    public Category getCategoryById(Long cat_Id) {
        return categoryDao.getCategoryById(cat_Id);
    }

    @Override
    public boolean insertCategory(Category cat) {
        return categoryDao.insertCategory(cat);
    }

    @Override
    public boolean updateCategory(Category cat) {
        return categoryDao.updateCategory(cat);
    }

    @Override
    public boolean deleteCategory(Long cat_Id) {
        return categoryDao.deleteCategory(cat_Id);
    }

    @Override
    public List<Category> getCategoryByName(String name, Integer currentPage, Integer size) {
        return categoryDao.getCategoryByName(name,currentPage,size);
    }

    @Override
    public List<Category> sortByName(Integer currentPage, Integer size) {
        return categoryDao.sortByName(currentPage,size);
    }

    @Override
    public Long countAllCategory() {
        return categoryDao.countAllCategory();
    }

    @Override
    public Long countCategoryByName(String name) {
        return categoryDao.countCategoryByName(name);
    }
}
