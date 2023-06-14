package com.example.demo.dao;

import com.example.demo.model.AdminUserInfo;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    public List<AdminUserInfo> getList(@Param("psize") Integer psize, @Param("offset") int offset);

    public int getTotalCount();

    public int deleteAdmin(@Param("id") Integer id);

}
