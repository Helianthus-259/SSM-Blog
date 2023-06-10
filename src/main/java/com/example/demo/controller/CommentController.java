package com.example.demo.controller;

import com.example.demo.common.AjaxResult;
import com.example.demo.common.SessionUtil;
import com.example.demo.model.CommentInfo;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/list")
    public List<CommentInfo> getCommentList(Integer aid){
        if(aid!=null&& aid >0){
            return commentService.getList(aid);
        }
        return null;
    }
    @RequestMapping("/add")
    public int add(Integer aid, String content, HttpServletRequest request){
        //todo:非空校验
        System.out.println(content);
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if(userInfo!= null && userInfo.getId()>0){
            return commentService.add(userInfo.getId(),aid,content);
        }
        return 0;
    }

    @RequestMapping("/delete")
    public int delete(Integer id){
        if(id!=null&& id >0){
            return commentService.delete(id);
        }
        return 0;
    }

}
