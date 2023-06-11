package com.example.demo.dao;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public int add(@Param("username") String username,
                   @Param("password") String password,@Param("githubaddress") String githubaddress);

    public UserInfo login(@Param("username") String username,
                          @Param("password") String password);

    public UserInfo searchByUid(@Param("uid") Integer uid);

    public UserInfo getUserByName(@Param("username") String username);

    public void updateByUid(@Param("id") Integer id,@Param("username") String username,
                            @Param("githubaddress") String githubaddress,@Param("password") String password);

    public UserInfo uploadphoto(@Param("photo") byte[] photo);
}