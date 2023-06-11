package com.example.demo.service;

import com.example.demo.dao.CommentMapper;
import com.example.demo.model.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

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
}
