package com.company.app.common.services;

import javax.servlet.http.HttpServletRequest;

import com.company.app.users.model.UserModel;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ApiBaseService {
    
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request;
        } else {
            return null;
        }
    }
    public UserModel getCurrentUser(){
        HttpServletRequest request = getServletRequest();
        UserModel user = (UserModel) request.getAttribute("userInfo");
        return user;
    }
}
