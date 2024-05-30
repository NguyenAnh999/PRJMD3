package com.ra.demo9.repository;

import com.ra.demo9.model.entity.Comment;
import com.ra.demo9.model.entity.WishList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CommentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Comment> getAllComment(Long productId){
        Session session = sessionFactory.openSession();
        List<Comment> commentList = session.createQuery("from Comment c where c.product.productId =:productId", Comment.class)
                .setParameter("productId",productId)
                .getResultList();
        return commentList;
    }

    public boolean addComment(Comment comment) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(comment);
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

    public double calculateAverageRating(Long productId) {
        List<Comment> comments = getAllComment(productId);
        if (comments.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Comment comment : comments) {
            sum += comment.getStar();
        }

        double avgRating = sum / comments.size();
        return Math.round(avgRating * 10.0) / 10.0;
    }
}
