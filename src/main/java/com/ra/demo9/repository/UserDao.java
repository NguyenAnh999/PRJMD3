package com.ra.demo9.repository;

import com.ra.demo9.model.entity.Users;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    @Autowired
    SessionFactory sessionFactory;
    public Users findById(Long id) {
     Session session = sessionFactory.openSession();
     try {
         return session.get(Users.class, id);
     }catch (Exception e) {
         throw new RuntimeException(e);
     }finally {
         session.close();
     }
    }
    public Users getUser(String username, String password) {
        Session session = sessionFactory.openSession();
        Users user = session.createQuery("from Users u where u.username =: username and u.password=: password", Users.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        session.close();
        return user;
    }

    public Users getUserById(long id) {
        Session session = sessionFactory.openSession();
        Users user = session.get(Users.class, id);
        session.close();
        return user;
    }


    public void addUser(Users user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void updateUser(Users user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<Users> getUsersSortByName(int currentPage, int size) {
        Session session = sessionFactory.openSession();
        List<Users> users = session.createQuery("from Users order by fullName", Users.class)
                .setFirstResult(currentPage * size)
                .setMaxResults(size).list();
        session.close();
        return users;
    }


    public List<Users> getAllUsers(int currentPage, int size) {
        Session session = sessionFactory.openSession();

        List<Users> users = session.createQuery("from Users", Users.class)
                .setFirstResult(currentPage * size)
                .setMaxResults(size).list();
        session.close();
        return users;
    }


    public List<Users> findUsersByName(String name, int currentPage, int size) {
        Session session = sessionFactory.openSession();
        name = "%" + name + "%";
        return session.createQuery("from Users u where u.username like :name")
                .setParameter("name", name).setFirstResult(currentPage * size)
                .setMaxResults(size).list();
    }

    public Long countUser() {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(u.id) from Users u").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public Long countUserByName(String name) {
        Session session = sessionFactory.openSession();
        name = "%" + name + "%";
        try {
            return (Long) session.createQuery("select count(u.id) from Users u where u.username like :name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public Users getUserByEmailName( String email) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Users where email =: email ", Users.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean existByEmail(String email) {
        Session session = sessionFactory.openSession();
        try {
            Long count = 0L;
            count = (Long) session.createQuery("select count(u.id) from Users u where u.email like :email")
                    .setParameter("email", email)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }


    public boolean existByUsername(String username) {
        Session session = sessionFactory.openSession();
        try {
            Long count = 0L;
            count = (Long) session.createQuery("select count(u.id) from Users u where u.username like :username")
                    .setParameter("username", username)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }




}

