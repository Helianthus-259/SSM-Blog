package com.example.demo.model;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private String photo;
    private String githubaddress;
    private String createtime;
    private String updatetime;
    private String state;
}
