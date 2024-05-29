package com.ra.demo9.config;


import com.ra.demo9.model.entity.RoleName;
import com.ra.demo9.model.entity.Users;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthUser implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Users user = (Users) request.getSession().getAttribute("user");
        if (user != null) {
            if(user.getRoleList().stream().anyMatch(role -> role.getRoleName().equals(RoleName.USER))){
                return true;
            }else {
                response.sendRedirect("/403");
                return false;
            }
        }else {
            response.sendRedirect("/loginUser");
            return false;
        }

    }

}
