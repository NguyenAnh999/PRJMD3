package com.ra.demo9.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Column(name = "full_address", nullable = false)
    @NotEmpty
    private String fullAddress;

    @Column(name = "phone", nullable = false)
    @NotEmpty
    private String phone;

    @Column(name = "receive_name", nullable = false)
    @NotEmpty
    private String receiveName;
}

