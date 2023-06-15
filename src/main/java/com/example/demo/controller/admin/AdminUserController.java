package com.example.demo.controller.admin;

import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Integer psize = Integer.parseInt((String) params.get("limit"));
        Integer pnum = Integer.parseInt((String) params.get("page"));
        System.out.println("pnun"+pnum);
        System.out.println("psize"+psize);
        return ResultGenerator.genSuccessResult(userService.getUserPage(psize, pnum));
    }

    @GetMapping("/users")
    public String list(HttpServletRequest request) {
        Object loginUserId = request.getSession().getAttribute("loginUserId");
        if(loginUserId==null) return "redirect:/admin/login";
        request.setAttribute("path", "users");
        return "admin/user";
    }

    @PostMapping("/users/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (userService.deleteListUser(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("刪除失败");
        }
    }
}
