package com.ra.demo9.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.nio.file.Path;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UsersDTO {
    @NotBlank(message = "Tên người dùng là bắt buộc")
    @Size(min = 3, max = 50, message = "Tên người dùng phải từ 3 đến 50 ký tự")
    private String username;
    @NotBlank(message = "Email là bắt buộc")
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotBlank(message = "Họ và tên là bắt buộc")
    @Size(max = 100, message = "Họ và tên phải ít hơn 100 ký tự")
    private String fullName;
    @NotBlank(message = "Mật khẩu là bắt buộc")
    @Size(min = 6, message = "Mật khẩu phải ít nhất 6 ký tự")
    private String password;
    private Boolean status;
    private MultipartFile avatar;
    @NotBlank(message = "sdt là bắt buộc")
    private String phone;
}
