package com.example.demo.controller;

import com.example.demo.common.*;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static com.example.demo.common.FileUtils.delAllFile;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

//    @RequestMapping("/avatarInfo")
//    public Object avatarInfo(HttpServletRequest request) {
//        UserInfo userInfo = SessionUtil.getLoginUser(request);
//        if(userInfo!= null && userInfo.getId()>0){
//            UserInfo avatarURL = userService.getAvatarURL(userInfo.getId());
//            return AjaxResult.success(avatarURL.getPhoto());
//        }
//        return null;
//    }
    @RequestMapping("/avatarInfo")
    public Object avatarInfo(HttpServletRequest request) {
        UserInfo userInfo = SessionUtil.getLoginUser(request);

        if(userInfo!=null && userInfo.getId()>0){
            UserInfo avatarURL = userService.getAvatarURL(userInfo.getId());
            if(avatarURL!=null && avatarURL.getId()>0){
                return AjaxResult.success(avatarURL.getPhoto());
            }
        }
        return null;
    }

    @PostMapping("/uploadPhoto")
    public Object uploadPhoto(MultipartFile photo, HttpServletRequest request) throws IOException{
        String url = FileUtils.uploads(photo);
        UserInfo userInfo = SessionUtil.getLoginUser(request);

        // 将头像url更新进入数据库，并响应前端上传头像请求
        if(userInfo!= null && userInfo.getId()>0){
            userService.uploadPhoto(url, userInfo.getId());
            return AjaxResult.success(url);
        }
        return null;
    }


    @RequestMapping("/reg")

    public Object reg(String username,String password,String githubaddress){


        if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password)){
            return AjaxResult.fail(-1,"非法请求!");
        }

        UserInfo u = userService.getUserByName(username);
        if(u!=null) return AjaxResult.fail(-1,"用户名重复!");

        int result = userService.add(username, SecurityUtil.encrypt(password),githubaddress);
        if(result == 1){
            return AjaxResult.success(1);
        }
        return AjaxResult.fail(-1,"注册失败!");
    }


    @RequestMapping("/login")
    public Object login(HttpServletRequest request, String username, String password){
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password)){
            return AjaxResult.fail(-1,"非法请求!");
        }

        UserInfo userInfo = userService.getUserByName(username);
        System.out.println(userInfo);
        if(userInfo == null || userInfo.getId()<=0){
            return AjaxResult.fail(-1,"登录失败!");
        }else{

            boolean result = SecurityUtil.decrypt(password,userInfo.getPassword());
            if(result){
                HttpSession session = request.getSession();
                session.setAttribute(Constant.SESSION_USERINFO_KEY,userInfo);
                return AjaxResult.success(1);
            }
        }
        return AjaxResult.fail(-1,"登陆失败!");
    }

    @RequestMapping("/logout")
    public Boolean logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute(Constant.SESSION_USERINFO_KEY)!=null){
            session.removeAttribute(Constant.SESSION_USERINFO_KEY);
        }
        return true;
    }

    @RequestMapping("/myinfo")
    public UserInfo myinfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute(Constant.SESSION_USERINFO_KEY)!=null){
            return (UserInfo) session.getAttribute(Constant.SESSION_USERINFO_KEY);
        }
        return null;
    }


    @RequestMapping("/myinfobyuid")
    public UserInfo myinfobyuid(Integer uid){

        if(uid!=null && uid>0){
            return userService.searchByUid(uid);
        }

        return null;
    }






    @RequestMapping("/photoinfo")
    public UserInfo photoinfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute(Constant.SESSION_USERINFO_KEY)!=null){
            return (UserInfo) session.getAttribute(Constant.SESSION_USERINFO_KEY);
        }
        return null;
    }

    @RequestMapping("/update")
    public Object upd(HttpServletRequest request,String username,String githubaddress,String oldpassword,String newpassword){
        System.out.println(username);
        System.out.println(githubaddress);
        System.out.println(oldpassword);
        System.out.println(newpassword);
        UserInfo u;
        String p =null;
        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute(Constant.SESSION_USERINFO_KEY)!=null){
            u = (UserInfo) session.getAttribute(Constant.SESSION_USERINFO_KEY);
            System.out.println(u.getPassword());
            if(oldpassword != null&& newpassword!=null&&oldpassword!=""&&newpassword!=""){
                if(SecurityUtil.decrypt(oldpassword,u.getPassword())){
                    p =SecurityUtil.encrypt(newpassword);
                }
                else{
                    return AjaxResult.fail(-2,"密码错误");
                }
            }
            userService.UpdateById(u.getId(),username,githubaddress,p);
            return AjaxResult.success(1);
        }
        else{
            return AjaxResult.fail(-1,"未登录");
        }

    }
}
