package com.example.demo.service;

import com.example.demo.dao.AdminUserMapper;
import com.example.demo.model.AdminUserInfo;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    public AdminUserInfo login(String username, String password){
        return adminUserMapper.login(username,password);
    }

    public AdminUserInfo getAdminUserByName(String username){
        return adminUserMapper.getAdminUserByName(username);
    }
}
