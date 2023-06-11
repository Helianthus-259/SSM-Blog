package com.example.demo.dao;

import com.example.demo.model.CommentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface CommentMapper {

    public  List<CommentInfo> getList(@Param("aid") Integer aid);
    public int getTotalCount();
    public int add(@Param("aid") Integer aid,@Param("uid") Integer uid,
                   @Param("content") String content,@Param("createtime") Date createtime);

    public int delete(@Param("id") Integer id);

    public  List<CommentInfo> getCommentList(@Param("psize") Integer psize, @Param("offset") int offset);
}
