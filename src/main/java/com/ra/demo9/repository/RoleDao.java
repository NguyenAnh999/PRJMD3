package com.ra.demo9.repository;

import com.ra.demo9.model.entity.Role;
import com.ra.demo9.model.entity.RoleName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {
    @Autowired
    SessionFactory sessionFactory;
    public Role getRoleName(RoleName name) {
        Session session = sessionFactory.openSession();
        return (Role) session.createQuery("from Role r where r.roleName=:name")
                .setParameter("name", name).uniqueResult();
    }
}
