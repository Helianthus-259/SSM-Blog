package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class AdminArticleinfo {
    private int id;
    private String title;
    private String content;
    private String createtime;
    private String updatetime;
    private int uid;
    private int rcount;
    private String state;
    private String username;
}

//public List<AdminArticleinfo> getlist(List<ArticleInfo> a){
//
//}