package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class AdminArticleinfo {
    private ArticleInfo articleInfo;
    private String username;
}

//public List<AdminArticleinfo> getlist(List<ArticleInfo> a){
//
//}