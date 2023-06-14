package com.example.demo.dao;

import com.example.demo.model.CommentInfo;
import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    public int add(@Param("username") String username,
                   @Param("password") String password,
                   @Param("githubaddress") String githubaddress,
                   @Param("createtime") Date createtime);

    public UserInfo login(@Param("username") String username,
                          @Param("password") String password);

    public UserInfo searchByUid(@Param("uid") Integer uid);

    public UserInfo getUserByName(@Param("username") String username);

    public void updateByUid(@Param("id") Integer id,@Param("username") String username,
                            @Param("githubaddress") String githubaddress,@Param("password") String password);
    public int getTotalCount();
    public int delete(@Param("id") Integer id);
    public List<UserInfo> getUserList(@Param("psize") Integer psize, @Param("offset") int offset);
    public void uploadPhoto(@Param("photo") String photo, @Param("id") Integer id);

    public UserInfo getAvatarURL(@Param("id") Integer id);
}