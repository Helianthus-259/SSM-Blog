package com.example.demo.model;

import lombok.Data;

@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private byte[] photo;
    private String githubaddress;
    private String createtime;
    private String updatetime;
    private String state;
}
