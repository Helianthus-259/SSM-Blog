package com.example.demo.service;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.dao.CommentMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.AdminCommentinfo;
import com.example.demo.model.CommentInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    public int add(String username, String password, String githubaddress, Date createtime){

        return userMapper.add(username,password,githubaddress, createtime);
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

    public List<UserInfo> getUserList(Integer psize, int offset){
        System.out.println(offset);
        return userMapper.getUserList(psize,offset);
    }

    public int getTotalCount(){{
        return userMapper.getTotalCount();
    }}
    public PageResult getCommentPage(Integer psize, int pnum){
//        System.out.println(pnum);
//        System.out.println(psize);
//        System.out.println((pnum-1)*psize);
        List<UserInfo> l = getUserList(psize,(pnum-1)*psize);
        PageResult pageResult = new PageResult(l, userMapper.getTotalCount(),psize,pnum);
        System.out.println(pageResult);
        return pageResult;
    }
    public int delete(Integer id){
        return userMapper.delete(id);
    }
    public Boolean deleteListUser(Integer[] uid){
        Boolean b = true;
        for (Integer num : uid) {
            if(userMapper.delete(num)<0) b=false;
            if(commentMapper.deleteByuid(num)<0) b=false;
            if(articleMapper.deleteArcByuid(num)<0) b=false;
        }
        return b;
    }
    public void uploadPhoto(String photo, Integer id) {
        userMapper.uploadPhoto(photo, id);
    }

    public UserInfo getAvatarURL(Integer id) { return userMapper.getAvatarURL(id); }
}

