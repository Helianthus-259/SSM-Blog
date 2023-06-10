package com.example.demo.dao;

import com.example.demo.model.CommentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    public  List<CommentInfo> getList(@Param("aid") Integer aid);
    public int getTotalCount();
    public int add(@Param("aid") Integer aid,@Param("uid") Integer userid,
                   @Param("content") String content);

    public int delete(@Param("id") Integer id);
}
