package com.example.demo.service;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.dao.CommentMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.AdminCommentinfo;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.CommentInfo;
import com.example.demo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.model.UserInfo;
import com.example.demo.model.ArticleInfo;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    public List<CommentInfo> getList(Integer aid){
        return commentMapper.getList(aid);
    }

    public int getTotalCount(){
        return commentMapper.getTotalCount();
    }
    public int add(Integer aid, Integer uid, String content, Date createtime){
        return commentMapper.add(aid, uid, content, createtime);
    }
    public int delete(Integer id){
        return commentMapper.delete(id);
    }

    public int deleteByuid(Integer uid){ return commentMapper.deleteByuid(uid);}

    public PageResult getCommentPage(Integer psize, int pnum){
//        System.out.println(pnum);
//        System.out.println(psize);
//        System.out.println((pnum-1)*psize);
        List<CommentInfo> l = getCommentList(psize,(pnum-1)*psize);
        //System.out.println(l);
        List<AdminCommentinfo> ll = getAdminCommentInfoList(l);
        PageResult pageResult = new PageResult(ll,commentMapper.getTotalCount(),psize,pnum);
        System.out.println(pageResult);
        return pageResult;
    }

    public List<CommentInfo> getCommentList(Integer psize, int offset){
        System.out.println(offset);
        return commentMapper.getCommentList(psize,offset);
    }

    public Boolean deleteListComment(Integer[] cid){
        Boolean b = true;
        for (Integer num : cid) {
            if(commentMapper.delete(num)<0) b=false;
        }
        return b;
    }

    public List<AdminCommentinfo> getAdminCommentInfoList(List<CommentInfo> commentInfoList){
        List<AdminCommentinfo> adminCommentinfoList = new ArrayList<>();
        for (CommentInfo commentInfo : commentInfoList) {
            AdminCommentinfo adminCommentinfo = new AdminCommentinfo();
            adminCommentinfo.setId(commentInfo.getId());
            adminCommentinfo.setAid(commentInfo.getAid());
            adminCommentinfo.setUid(commentInfo.getUid());
            adminCommentinfo.setContent(commentInfo.getContent());
            adminCommentinfo.setCreatetime(commentInfo.getCreatetime());
            // 在这里设置 username
            UserInfo userInfo = userMapper.searchByUid(commentInfo.getUid());
            if(userInfo!=null && userInfo.getId()>0){
                adminCommentinfo.setUsername(userInfo.getUsername());
            }

            // 在这里设置 articleTitle
            ArticleInfo articleInfo = articleMapper.getDetail(commentInfo.getAid());
            if(articleInfo!=null && articleInfo.getId()>0){
                adminCommentinfo.setArticleTitle(articleInfo.getTitle());
            }
            adminCommentinfoList.add(adminCommentinfo);
        }
        return adminCommentinfoList;
    }

}
