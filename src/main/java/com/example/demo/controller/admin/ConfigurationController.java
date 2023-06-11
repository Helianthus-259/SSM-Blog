package com.example.demo.controller.admin;

import com.example.demo.common.SecurityUtil;
import com.example.demo.model.AdminUserInfo;
import com.example.demo.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;

@Controller
@RequestMapping("/admin")
public class ConfigurationController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/configurations")
    public String list(HttpServletRequest request) {
        Object loginUserId = request.getSession().getAttribute("loginUserId");
        if(loginUserId==null) return "redirect:/admin/login";
        request.setAttribute("path", "configurations");
        //request.setAttribute("configurations", configService.getAllConfigs());
        return "admin/configuration";
    }

    @PostMapping("/configurations/adduserInfo")
    @ResponseBody
    public Result userInfo(@RequestParam(value = "yourName") String yourName,
                           @RequestParam(value = "yourNickname") String yourNickname,
                           @RequestParam(value = "yourPassword") String yourPassword,
                           @RequestParam(value = "yourPassword1") String yourPassword1) {
        System.out.println(yourName);
        System.out.println(yourNickname);
        System.out.println(yourPassword);
        System.out.println(yourPassword1);
        if(yourName.equals("")||yourName==null||yourNickname.equals("")||yourNickname==null||yourPassword.equals("")||yourPassword==null||
                yourPassword1.equals("")||yourPassword1==null){
            return ResultGenerator.genFailResult("不能留空！");
        }
        if(yourName.equals("请输入登录名称")||yourNickname.equals("请输入昵称")||yourPassword.equals("请输入密码")||yourPassword1.equals("再次输入密码")){
            return ResultGenerator.genFailResult("无效参数！");
        }
        if(!yourPassword.equals(yourPassword1)) return ResultGenerator.genFailResult("两次密码输入不相同！");
        AdminUserInfo adminUserInfo = adminUserService.getAdminUserByName(yourName);
        if(adminUserInfo!=null) return ResultGenerator.genFailResult("登录名重复！");
        if(adminUserService.add(yourName, SecurityUtil.encrypt(yourPassword),yourNickname)){
            return ResultGenerator.genSuccessResult("Success");
        }
        return ResultGenerator.genFailResult("添加失败");
    }

}
