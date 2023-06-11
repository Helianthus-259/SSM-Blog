package com.example.demo.controller.admin;

import com.example.demo.common.SecurityUtil;
import com.example.demo.model.AdminUserInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.service.AdminUserService;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping({"/login"})
    public String login() {
        return "admin/login";
    }

    @GetMapping({"/index"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
//        request.setAttribute("categoryCount", categoryService.getTotalCategories());
        request.setAttribute("blogCount",articleService.getTotalCount());
//        request.setAttribute("linkCount", linkService.getTotalLinks());
//        request.setAttribute("tagCount", tagService.getTotalTags());
        //request.setAttribute("commentCount", commentService.getTotalCount());
        //System.out.println(commentService.getTotalCount());
        request.setAttribute("path", "index");
        return "admin/index";
    }


    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpSession session) {
        System.out.println(userName);
        System.out.println(password);
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        AdminUserInfo adminUserInfo = adminUserService.getAdminUserByName(userName);
        if (adminUserInfo != null) {
            Boolean result = SecurityUtil.decrypt(password,adminUserInfo.getPassword());
            if(result){
                session.setAttribute("loginUser", adminUserInfo.getNickName());
                session.setAttribute("loginUserId", adminUserInfo.getId());
                return "redirect:/admin/index";
            }
            session.setAttribute("errorMsg", "登陆失败");
            return "admin/login";
        } else {
            session.setAttribute("errorMsg", "登陆失败");
            return "admin/login";
        }
    }
}
