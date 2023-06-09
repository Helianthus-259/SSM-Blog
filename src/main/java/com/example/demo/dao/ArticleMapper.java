package com.example.demo.dao;

import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

    public List<ArticleInfo> getList(@Param("psize") Integer psize, @Param("offset") int offset);


    public List<ArticleInfo> getListByKeyword(@Param("psize") Integer psize, @Param("offset") int offset,@Param("title") String title);

    public List<ArticleInfo> getMyList(@Param("uid") Integer uid);

    public ArticleInfo getDetail(@Param("aid") Integer aid);

    public int updateCount(@Param("aid") Integer aid);

    public int deleteArc(@Param("aid") Integer aid);

    public int deleteArcByuid(@Param("uid") Integer uid);

    public int update(@Param("aid") Integer aid,
                      @Param("uid") Integer uid,
                      @Param("title") String title,
                      @Param("content") String content);

    public int getTotalCount();

    public int getTotalCountBykeyword(@Param("title") String title);

    public int add(@Param("uid") Integer uid,@Param("title") String title,
                   @Param("content") String content,@Param("rcount") Integer rcount,
                   @Param("createtime") Date createtime);
}

