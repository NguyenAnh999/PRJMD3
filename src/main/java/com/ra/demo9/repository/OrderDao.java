package com.ra.demo9.repository;

import com.ra.demo9.model.entity.Order;
import com.ra.demo9.model.entity.OrderDetails;
import com.ra.demo9.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    SessionFactory sessionFactory;
    public List<Order> findAll() {
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createQuery("from Order",Order.class).list();
        session.close();
        return orders;
    }
    public Order findById(Long id) {
        Session session = sessionFactory.openSession();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }
    public void save(Order order) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(order);
        tx.commit();
        session.close();
    }
    public void update(Order order) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(order);
        tx.commit();
        session.close();
    }
    public void saveDetail(OrderDetails order) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(order);
        tx.commit();
        session.close();
    }
    public List<Product> findByProductId(Long orderId) {
        Session session = sessionFactory.openSession();
        List<Product> list = session
                .createQuery("SELECT p from Product p join OrderDetails od on p.productId = od.product.productId join Order o on od.order.orderId = o.orderId where o.orderId=:orderId", Product.class)
                .setParameter("orderId",orderId).getResultList();
        return list;
    }
}
