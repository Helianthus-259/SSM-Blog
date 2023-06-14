package com.example.demo.service;

import com.example.demo.dao.AdminUserMapper;
import com.example.demo.model.AdminArticleinfo;
import com.example.demo.model.AdminUserInfo;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AdminUserInfo> getList(Integer psize,int offset){
        return adminUserMapper.getList(psize,offset);
    }

    public int getTotalCount(){
        return adminUserMapper.getTotalCount();
    }

    public PageResult getAdminUsersPage(Integer psize, int pnum){
        List<AdminUserInfo> l = getList(psize,(pnum-1)*psize);
        PageResult pageResult = new PageResult(l,adminUserMapper.getTotalCount(),psize,pnum);
        System.out.println(pageResult);
        return pageResult;
    }

    public Boolean deleteAdmin(Integer id){
        return adminUserMapper.deleteAdmin(id)>0;
    }
    public Boolean deleteAdminList(Integer[] id){
        Boolean b = true;
        for (Integer num : id) {
            if(!deleteAdmin(num)) b=false;
        }
        return b;
    }
}
