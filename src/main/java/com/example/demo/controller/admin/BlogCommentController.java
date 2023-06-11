package com.example.demo.controller.admin;

import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class BlogCommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Integer psize = Integer.parseInt((String) params.get("limit"));
        Integer pnum = Integer.parseInt((String) params.get("page"));
        System.out.println(pnum);
        System.out.println(psize);
        return ResultGenerator.genSuccessResult(commentService.getCommentPage(psize,pnum));
    }

    @GetMapping("/comments")
    public String list(HttpServletRequest request) {
        Object loginUserId = request.getSession().getAttribute("loginUserId");
        if(loginUserId==null) return "redirect:/admin/login";
        request.setAttribute("path", "comments");
        return "admin/comment";
    }

    @PostMapping("/comments/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (commentService.deleteListComment(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("刪除失败");
        }
    }

}
