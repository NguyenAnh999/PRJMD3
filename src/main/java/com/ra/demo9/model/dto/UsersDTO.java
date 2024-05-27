package com.ra.demo9.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UsersDTO {
    private String username;
    private String email;
    private String fullName;
    private String password;
    private Boolean status;
    private MultipartFile avatar;
    private String phone;
}
