package com.ra.demo9.repository;


import com.ra.demo9.model.entity.Voucher;
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
    private JdbcTemplate jdbcTemplate;

    public List<Voucher> findAll() {
        String sql = "SELECT * FROM voucher";
        return jdbcTemplate.query(sql, new VoucherRowMapper());
    }

    public Voucher findByCode(String code) {
        String sql = "SELECT * FROM voucher WHERE code = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{code}, new VoucherRowMapper());
    }

    public void save(Voucher voucher) {
        String sql = "INSERT INTO voucher (code, discount_amount, expiry_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, voucher.getCode(), voucher.getDiscountAmount(), voucher.getExpiryDate());
    }

    private static final class VoucherRowMapper implements RowMapper<Voucher> {
        @Override
        public Voucher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Voucher voucher = new Voucher();
            voucher.setVoucherId(rs.getLong("voucher_id"));
            voucher.setCode(rs.getString("code"));
            voucher.setDiscountAmount(rs.getDouble("discount_amount"));
            voucher.setExpiryDate(rs.getDate("expiry_date"));
            return voucher;
        }
    }
}

