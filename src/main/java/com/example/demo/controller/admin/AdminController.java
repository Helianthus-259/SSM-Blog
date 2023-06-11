package com.example.demo.controller.admin;

import com.example.demo.common.AjaxResult;
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
import org.springframework.web.bind.annotation.*;

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
        Object loginUserId = request.getSession().getAttribute("loginUserId");
        if(loginUserId==null) return "redirect:/admin/login";
        request.setAttribute("path", "index");
//        request.setAttribute("categoryCount", categoryService.getTotalCategories());
        request.setAttribute("blogCount",articleService.getTotalCount());
//        request.setAttribute("linkCount", linkService.getTotalLinks());
//        request.setAttribute("tagCount", tagService.getTotalTags());
        request.setAttribute("commentCount", commentService.getTotalCount());
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
                session.setAttribute("loginUserName",adminUserInfo.getUsername());
                return "redirect:/admin/index";
            }
            session.setAttribute("errorMsg", "登陆失败");
            return "admin/login";
        } else {
            session.setAttribute("errorMsg", "登陆失败");
            return "admin/login";
        }
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUserInfo adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getUsername());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public Object nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
            return AjaxResult.fail(-1,"参数不能为空");
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            request.setAttribute("nickName",nickName);
            return AjaxResult.success(1);
        } else {
            return AjaxResult.fail(-1,"修改失败");
        }
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public Object passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return AjaxResult.fail(-1,"参数不能为空");
        }
        System.out.println(originalPassword);
        System.out.println(newPassword);
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        String username = (String) request.getSession().getAttribute("loginUserName");
        AdminUserInfo adminUserInfo = adminUserService.getAdminUserByName(username);
        Boolean result = SecurityUtil.decrypt(originalPassword,adminUserInfo.getPassword());
        if(result){
            if (adminUserService.updatePassword(loginUserId,  SecurityUtil.encrypt(newPassword))) {
                //修改成功后清空session中的数据，前端控制跳转至登录页
                request.getSession().removeAttribute("loginUserId");
                request.getSession().removeAttribute("loginUser");
                request.getSession().removeAttribute("errorMsg");
                return AjaxResult.success(1);
            } else {
                return AjaxResult.fail(-1,"修改失败");
            }
        }
        return AjaxResult.fail(-1,"修改失败");
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("loginUserName");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }

}
