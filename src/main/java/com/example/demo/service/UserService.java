package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int add(String username,String password,String githubaddress){
        return userMapper.add(username,password,githubaddress);
    }

    public UserInfo login(String username, String password){
        return userMapper.login(username,password);
    }

    public UserInfo searchByUid(Integer uid){
        return userMapper.searchByUid(uid);
    }

    public UserInfo getUserByName(String username){
        return userMapper.getUserByName(username);
    }

    public void UpdateById(Integer id,String username,String githubaddress,String password){
        System.out.println(username);
        System.out.println(githubaddress);
        System.out.println(password);
        userMapper.updateByUid(id,username,githubaddress,password);
    }
}

