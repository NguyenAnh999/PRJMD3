package com.ra.demo9.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "serial_number", nullable = false, unique = true, length = 100)
    private String serialNumber;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "note", length = 100)
    private String note;

    @Column(name = "receive_name", length = 100)
    private String receiveName;

    @Column(name = "receive_address", length = 255)
    private String receiveAddress;

    @Column(name = "receive_phone", length = 15)
    private String receivePhone;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "received_at")
    private Date receivedAt;

}
