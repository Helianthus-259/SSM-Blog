package com.example.demo.model;

import lombok.Data;

@Data
public class AdminCommentinfo {
    private int id;
    private int aid;
    private int uid;
    private String content;
    private String createtime;
    private String username;
    private String articleTitle;
}
