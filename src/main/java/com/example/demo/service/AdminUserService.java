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

    public AdminUserInfo getUserDetailById(Integer id){
        return adminUserMapper.getAdminUserById(id);
    }

    public Boolean updateName(int id,String username,String nickName){
        return  adminUserMapper.updateName(id,username,nickName)>0;
    }
    public Boolean updatePassword(int id,String password){
        return  adminUserMapper.updatePassword(id,password)>0;
    }
    public Boolean add(String username,String password,String nickName){
        return adminUserMapper.add(username,password,nickName)>0;
    }
}
