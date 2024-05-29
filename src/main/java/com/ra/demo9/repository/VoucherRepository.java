package com.ra.demo9.repository;


import com.ra.demo9.model.entity.Voucher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VoucherRepository {
    @Autowired
    SessionFactory sessionFactory;

    public List<Voucher> findAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("from Voucher").list();
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    public Voucher findByCode(String code) {

        Session session = sessionFactory.openSession();
        try{
            return session.get(Voucher.class, code);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    public void save(Voucher voucher) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(voucher);
        session.getTransaction().commit();
        session.close();
    }

//    private static final class VoucherRowMapper implements RowMapper<Voucher> {
//        @Override
//        public Voucher mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Voucher voucher = new Voucher();
//            voucher.setVoucherId(rs.getLong("voucher_id"));
//            voucher.setCode(rs.getString("code"));
//            voucher.setDiscountAmount(rs.getDouble("discount_amount"));
//            voucher.setExpiryDate(rs.getDate("expiry_date"));
//            return voucher;
//        }
//    }
}