package com.ra.demo9.repository;

import com.ra.demo9.model.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    SessionFactory sessionFactory;
    public List<Product> findAll() {
        sessionFactory.openSession();
        List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product ").list();
        sessionFactory.close();
        return products;
    }
    public Product findById(int id) {
        sessionFactory.openSession();
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        sessionFactory.close();
        return product;
    }
    public void save(Product product) {
        sessionFactory.openSession();
        sessionFactory.getCurrentSession().save(product);
        sessionFactory.close();
    }
    public void update(Product product) {
        sessionFactory.openSession();
        sessionFactory.getCurrentSession().update(product);
        sessionFactory.close();
    }
    public void delete(Product product) {
        sessionFactory.openSession();
        sessionFactory.getCurrentSession().delete(product);
        sessionFactory.close();
    }
}
