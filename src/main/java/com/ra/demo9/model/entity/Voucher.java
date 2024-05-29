package com.ra.demo9.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "/voucher")
public class Voucher

{
    @Id
    private Long voucherId;
    private String code;
    private double discountAmount;
    private Date expiryDate;


}
