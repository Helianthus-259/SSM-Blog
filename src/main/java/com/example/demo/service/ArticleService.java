package com.example.demo.service;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.model.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public List<ArticleInfo> getMyList(Integer uid){
        return articleMapper.getMyList(uid);
    }

    public ArticleInfo getDetail(Integer aid){
        articleMapper.updateCount(aid);
        return articleMapper.getDetail(aid);
    }

    public void deleteArc(Integer aid){
        articleMapper.deleteArc(aid);
    }

    public int update(Integer aid,Integer uid,String title,String content){
        return articleMapper.update(aid,uid,title,content);
    }

    public List<ArticleInfo> getList(Integer psize,int offset){
        return articleMapper.getList(psize,offset);
    }

    public List<ArticleInfo> getListByKeyword(Integer psize,int offset,String title){
        return articleMapper.getListByKeyword(psize,offset,title);
    }

    public int getTotalCount(){
        return articleMapper.getTotalCount();
    }

    public int getTotalCountBykeyword(String title){
        return articleMapper.getTotalCountBykeyword(title);
    }

    public int add(Integer uid, String title, String content, Date createtime){
        return articleMapper.add(uid,title,content,createtime);
    }
}
