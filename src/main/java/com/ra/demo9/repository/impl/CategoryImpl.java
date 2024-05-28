package com.ra.demo9.repository.impl;

import com.ra.demo9.model.entity.Category;
import com.ra.demo9.repository.ICategoryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryImpl implements ICategoryDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> getCategory() {
        Session session = sessionFactory.openSession();
        try{
            List list = session.createQuery("from Category ").list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Category getCategoryById(Long cat_Id) {
        Session session = sessionFactory.openSession();
        try{
            Category category = session.get(Category.class,cat_Id);
            return category;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean insertCategory(Category cat) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(cat);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateCategory(Category cat) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(cat);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Long cat_Id) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(getCategoryById(cat_Id));
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        Session session = sessionFactory.openSession();
        try{
            if(name==null || name.length()==0)
                name = "%";
            else
                name = "%"+name+"%";
            List list = session.createQuery("from Category where categoryName like : catName")
                    .setParameter("catName",name)
                    .list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
