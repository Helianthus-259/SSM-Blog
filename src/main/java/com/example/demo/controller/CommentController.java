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
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/list")
    public List<CommentInfo> getList(Integer aid){
        if(aid!=null&& aid >0){
            return commentService.getList(aid);
        }
        return null;
    }
    @RequestMapping("/add")
    public int add(Integer aid, String content, HttpServletRequest request){
        //todo:非空校验
        System.out.println(content);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());

        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if(userInfo!= null && userInfo.getId()>0){
            return commentService.add(aid, userInfo.getId(), content, new Date());
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
