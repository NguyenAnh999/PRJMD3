package com.ra.demo9.repository;

import com.ra.demo9.model.entity.Address;
import com.ra.demo9.model.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDao {
    @Autowired
    SessionFactory sessionFactory;

    public List<Address> getAllAddress(Long userid) {
        Session session = sessionFactory.openSession();
        List getAllAddress = session.createQuery("from Address where users.userId =:users")
                .setParameter("users", userid)
                .list();
        session.close();
        return getAllAddress;
    }
    public void addAddress(Address address){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(address);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(Address address){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(address);
        session.getTransaction().commit();
        session.close();
    }
}
