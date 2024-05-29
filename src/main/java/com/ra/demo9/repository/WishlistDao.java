package com.ra.demo9.repository;

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
        List<Product> productWishList = session.createQuery("from Product p where p.productId in (select w.productId from WishList w where w.userId =: userId )", Product.class)
                .setParameter("userId",userId)
                .getResultList();
        return productWishList;
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

    public void deleteWishList(Integer wishListId)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM WishList w WHERE w.wishListId =:wishListId" )
                .setParameter("wishListId", wishListId)
                .executeUpdate() ;
        session.getTransaction().commit();
        session.close();
    }

}
