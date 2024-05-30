package com.ra.demo9.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DashBroadDao {
    @Autowired
    SessionFactory sessionFactory;
    public Long getAllQuantitySale(){
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select count (o.product) from OrderDetails o", Long.class).getSingleResult();
        session.close();
        return count;
    }
    public Long getAllQuantityProduct(){
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select count(o) from Product o", Long.class).getSingleResult();
        session.close();
        return count;
    }
    public Double getAllPriceSale(){
        Session session = sessionFactory.openSession();
        Double PriceSale = session.createQuery("select sum(o.totalPrice) from Order o", Double.class).getSingleResult();
        session.close();
        return PriceSale;
    }
    public Long getCountAllUser(){
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select count(o) from Users o", Long.class).getSingleResult();
        session.close();
        return count;
    }
}
