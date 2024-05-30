package com.ra.demo9.repository;

import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.ShoppingCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartDao
{
    @Autowired
    private SessionFactory sessionFactory;

    public void addShoppingCart(ShoppingCart shoppingCart) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(shoppingCart);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    public double getShoppingCartTotal(Long userId)
    {
        Session session = sessionFactory.openSession();
        Double total= session.createQuery("SELECT SUM(s.orderQuantity * p.unitPrice) FROM ShoppingCart s  JOIN Product p on s.productId.productId = p.productId where s.userId.userId=:userId", Double.class)
                .setParameter("userId", userId)
                .getSingleResult();
        session.close();
        return total!=null?total:0.0;
    }


public List<Product> getShoppingProduct(Long userId)
    {
        Session session = sessionFactory.openSession();
        List<Product> list= session.createQuery("select p FROM Product p  JOIN ShoppingCart s   on s.productId.productId = p.productId where s.userId.userId=:userId", Product.class)
                .setParameter("userId", userId)
                .getResultList();
        session.close();
        return list;

    }

    public Long getShoppingCartTotalPrd(Long userId)
    {
        Session session = sessionFactory.openSession();
        Long total= session.createQuery("SELECT sum(s.orderQuantity) FROM ShoppingCart s  JOIN Product p on s.productId.productId = p.productId where s.userId.userId=:userId", Long.class)
                .setParameter("userId", userId)
                .getSingleResult();
        session.close();
        return total!=null?total:0;
    }

    public List<ShoppingCart> getAllShoppingCarts(Long userId){
        Session session = sessionFactory.openSession();
        List<ShoppingCart> shoppingCarts= session.createQuery("SELECT s FROM ShoppingCart s where s.userId.userId=:userId", ShoppingCart.class)
                .setParameter("userId", userId)
                .getResultList();
        session.close();
        return shoppingCarts;
    }

    public void deleteAllShoppingCart(Long userId)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM ShoppingCart s where s.userId.userId=:userId")
                .setParameter("userId", userId)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    public void deleteItemShoppingCart(Integer shoppingCartId)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM ShoppingCart s WHERE s.shoppingCartId =:shoppingCartId" )
                .setParameter("shoppingCartId", shoppingCartId)
                .executeUpdate() ;
        session.getTransaction().commit();
        session.close();
    }

}
