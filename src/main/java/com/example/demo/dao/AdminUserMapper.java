package com.example.demo.dao;

import com.example.demo.model.AdminUserInfo;
import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminUserMapper {

    public AdminUserInfo login(@Param("username") String username,
                                    @Param("password") String password);

    public AdminUserInfo getAdminUserByName(@Param("username") String username);

    public AdminUserInfo getAdminUserById(@Param("id") Integer id);

    public int updateName(@Param("id") int id,@Param("username") String username,
                          @Param("nickName") String nickName);

    public int updatePassword(@Param("id") int id,@Param("password") String password);

    public int add(@Param("username") String username,
                   @Param("password") String password,@Param("nickName") String nickName);
}
