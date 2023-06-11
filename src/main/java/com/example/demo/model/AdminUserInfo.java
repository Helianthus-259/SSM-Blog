package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class AdminUserInfo {
    private Integer id;

    private String username;

    private String password;

    private String nickName;

    private Byte locked;

}
