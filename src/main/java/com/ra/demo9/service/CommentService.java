package com.ra.demo9.service;

import com.ra.demo9.model.entity.Comment;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.repository.CommentDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public List<Comment> getAllComment(Long userId) {
        return commentDao.getAllComment(userId);
    }

    public boolean addComment(Comment comment)  {
        return commentDao.addComment(comment);
    }

    public double calculateAverageRating(Long productId){
        return commentDao.calculateAverageRating(productId);
    }
}