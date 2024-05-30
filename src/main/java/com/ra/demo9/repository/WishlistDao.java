package com.ra.demo9.repository;

import com.ra.demo9.model.entity.Category;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.ShoppingCart;
import com.ra.demo9.model.entity.WishList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistDao {
    @Autowired
    SessionFactory sessionFactory;
    public List<Product> getAllWishList(Long userId){
        Session session = sessionFactory.openSession();
        try {
            String hql = "select p from Product p where p.id in (select w.product.id from WishList w where w.user.id = :userId)";
            return session.createQuery(hql, Product.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }finally {
            session.close();
        }
    }

    public boolean addWishList(WishList wishList) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(wishList);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }
    public WishList getWishListById(Long wl_Id) {
        Session session = sessionFactory.openSession();
        try {
            WishList wishList = session.get(WishList.class,wl_Id);
            return wishList;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }public WishList getWishListByIdProduct(Long userId,Long productId) {
        Session session = sessionFactory.openSession();
        try {
            WishList wishList = session.createQuery("from WishList w where w.user.userId =:userId and w.product.productId=:productId", WishList.class)
                    .setParameter("userId",userId)
                    .setParameter("productId",productId)
                    .getSingleResult();
            return wishList;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    public boolean deleteWishList(Long userId,Long productId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE from WishList w where w.product.productId=:productId and w.user.userId=:userId")
                    .setParameter("userId",userId)
                            .setParameter("productId",productId)
                                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

}
