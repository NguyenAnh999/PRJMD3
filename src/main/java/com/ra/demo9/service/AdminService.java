package com.ra.demo9.service;

import com.ra.demo9.model.dto.UsersDTO;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminService {
    @Autowired
    UserDao userDao;
    @Autowired
    FileService fileService;
    public Users getUser(String name, String pass) {
        return userDao.getUser(name, pass);
    }
}
