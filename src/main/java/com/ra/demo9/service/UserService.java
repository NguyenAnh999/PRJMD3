package com.ra.demo9.service;

import com.ra.demo9.model.dto.UsersDTO;
import com.ra.demo9.model.entity.Role;
import com.ra.demo9.model.entity.RoleName;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.repository.RoleDao;
import com.ra.demo9.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final String FACEBOOK_USER_PREFIX = "facebook";
    @Autowired
    UserDao userDao;
    @Autowired
    FileService fileService;
    @Autowired
    RoleDao roleDao;

    public List<Users> findAll(int currentPage, int size) {
        return userDao.getAllUsers(currentPage, size);
    }

    public List<Users> findByName(String name, int currentPage, int size) {
        return userDao.findUsersByName(name, currentPage, size);
    }

    public List<Users> findOderByFullName(int currentPage, int size) {
        return userDao.getUsersSortByName(currentPage, size);
    }

    public void save(UsersDTO usersDTO, boolean isAdmin) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.getRoleName(RoleName.USER));

        if (isAdmin) {
            roleSet.add(roleDao.getRoleName(RoleName.ADMIN));
        }
        Users user = Users.builder()
                .email(usersDTO.getEmail())
                .password(usersDTO.getPassword())
                .username(usersDTO.getUsername())
                .phone(usersDTO.getPhone())
                .createdAt(new Date())
                .status(true)
                .isDeleted(true)
                .updatedAt(new Date())
                .avatar(fileService.uploadFileToServer(usersDTO.getAvatar()))
                .fullName(usersDTO.getFullName())
                .roleList(roleSet)
                .build();
        userDao.addUser(user);
    }

    public void update(UsersDTO usersDTO, boolean isAdmin, Long id) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.getRoleName(RoleName.USER));
        if (isAdmin) {
            roleSet.add(roleDao.getRoleName(RoleName.ADMIN));
        }
        Users user = Users.builder()
                .userId(id)
                .password(userDao.findById(id).getPassword())
                .email(usersDTO.getEmail())
                .username(usersDTO.getUsername())
                .phone(usersDTO.getPhone())
                .status(true)
                .createdAt(userDao.findById(id).getCreatedAt())
                .isDeleted(true)
                .updatedAt(new Date())
                .fullName(usersDTO.getFullName())
                .roleList(roleSet)
                .build();

        if (fileService.uploadFileToServer(usersDTO.getAvatar())!=null&& !fileService.uploadFileToServer(usersDTO.getAvatar()).isEmpty()) {
            user.setAvatar(fileService.uploadFileToServer(usersDTO.getAvatar()));
        }

        userDao.updateUser(user);
    }
    public Users findById(Long id) {
        return userDao.getUserById(id);
    }

    public void updateStatus(Users user) {
        if (user.getStatus()) {
            {
                user.setStatus(false);
            }
        } else {
            user.setStatus(true);
        }
        userDao.updateUser(user);
    }

    public Long getCountUser() {
        return userDao.countUser();
    }

    public Long getCountUserByName(String name) {
        return userDao.countUserByName(name);
    }

    public Users findOrCreateUser(String fullName, String email, String facebookId, String avatarUrl) {
        Users users = userDao.getUserByEmailName(email);
        if (users == null) {
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(roleDao.getRoleName(RoleName.USER));
            users = Users.builder()
                    .email(email)
                    .avatar(avatarUrl)
                    .fullName(fullName)
                    .username(FACEBOOK_USER_PREFIX + facebookId)
                    .roleList(roleSet)
                    .createdAt(new Date())
                    .status(true)
                    .isDeleted(true)
                    .updatedAt(new Date())
                    .password(facebookId)
                    .build();
            userDao.addUser(users);
        }
        return users;
    }
}