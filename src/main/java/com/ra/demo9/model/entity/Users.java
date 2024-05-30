package com.ra.demo9.model.entity;

import com.ra.demo9.model.validation.UniqueEmail;
import com.ra.demo9.model.validation.UniqueUsername;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Tên người dùng là bắt buộc")
    @Size(min = 3, max = 50, message = "Tên người dùng phải từ 3 đến 50 ký tự")
    @Column(name = "username")
    @UniqueUsername(message = "tài khoản đã tồn tại")
    private String username;

    @NotBlank(message = "Email là bắt buộc")
    @Email(message = "Email không hợp lệ")
    @Column(name = "email")
    @UniqueEmail(message = "email đã tồn tại")
    private String email;

    @NotBlank(message = "Họ và tên là bắt buộc")
    @Size(max = 100, message = "Họ và tên phải ít hơn 100 ký tự")
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "status")
    private Boolean status;

    @NotBlank(message = "Mật khẩu là bắt buộc")
    @Size(min = 6, message = "Mật khẩu phải ít nhất 6 ký tự")
    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @NotBlank(message = "sdt là bắt buộc")
    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roleList = new HashSet<>();

}
