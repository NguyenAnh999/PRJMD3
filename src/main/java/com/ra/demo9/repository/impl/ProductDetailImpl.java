package com.ra.demo9.repository.impl;


import com.ra.demo9.model.entity.ProductDetail;
import com.ra.demo9.repository.IProductDetailDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDetailImpl implements IProductDetailDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<ProductDetail> getProductDetail(Integer currentPage, Integer size) {
        Session session = sessionFactory.openSession();
        List<ProductDetail> productDetails = null;
        try {
            productDetails = session.createQuery("from ProductDetail ", ProductDetail.class)
                    .setFirstResult(currentPage*size)
                    .setMaxResults(size)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return productDetails;
    }

    @Override
    public ProductDetail getProductDetailById(Long proDetail_Id) {
        Session session = sessionFactory.openSession();
        try {
            ProductDetail productDetail = session.get(ProductDetail.class,proDetail_Id);
            return productDetail;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean insertProductDetail(ProductDetail proDetail) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(proDetail);
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

    @Override
    public boolean updateProductDetail(ProductDetail proDetail) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(proDetail);
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
    public boolean deleteProductDetail(Long proDetail_Id) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(getProductDetailById(proDetail_Id));
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
    public List<ProductDetail> findByProductId(Long productId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<ProductDetail> result = null;
        try {
            tx = session.beginTransaction();
            Query<ProductDetail> query = session.createQuery("from ProductDetail where product.id = :productId", ProductDetail.class);
            query.setParameter("productId", productId);
            result = query.getResultList();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }


    @Override
    public List<ProductDetail> getProductDetailByName(String name, Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public List<ProductDetail> sortByNameProductDetail(Integer currentPage, Integer size) {
        return null;
    }

    @Override
    public Long countAllProductDetail() {

        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(pd.id) from ProductDetail pd").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Long countProductDetailByName(String name) {
        return null;
    }
}
