package com.example.demo.service;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.AdminArticleinfo;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.util.PageQueryUtil;
import com.example.demo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.demo.util.PageResult;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    public List<ArticleInfo> getMyList(Integer uid){
        return articleMapper.getMyList(uid);
    }

    public ArticleInfo getDetail(Integer aid){
        articleMapper.updateCount(aid);
        return articleMapper.getDetail(aid);
    }

    public Boolean deleteArc(Integer aid){
        return articleMapper.deleteArc(aid)>0;
    }

    public Boolean deleteArcByuid(Integer uid){
        return articleMapper.deleteArcByuid(uid)>0;
    }
    public Boolean deleteArcList(Integer[] aid){
        Boolean b = true;
        for (Integer num : aid) {
            if(articleMapper.deleteArc(num)<0) b=false;
        }
        return b;
    }

    public int update(Integer aid,Integer uid,String title,String content){
        return articleMapper.update(aid,uid,title,content);
    }

    public List<ArticleInfo> getList(Integer psize,int offset){
        return articleMapper.getList(psize,offset);
    }

    public List<ArticleInfo> getListByKeyword(Integer psize,int offset,String title){
        return articleMapper.getListByKeyword(psize,offset,title);
    }

    public int getTotalCount(){
        return articleMapper.getTotalCount();
    }

    public int getTotalCountBykeyword(String title){
        return articleMapper.getTotalCountBykeyword(title);
    }

    public int add(Integer uid, String title, String content, Date createtime){
        return articleMapper.add(uid,title,content,createtime);
    }
//    public List<ArticleInfo> getAllList(Integer psize, int offset){
//        return articleMapper.getAllList(psize,offset);
//    }
    public PageResult getBlogsPage(Integer psize,int pnum){
        List<ArticleInfo> l = getList(psize,(pnum-1)*psize);
        //System.out.println(l);
        List<AdminArticleinfo> ll = getAdminArticleInfo(l);
        PageResult pageResult = new PageResult(ll,articleMapper.getTotalCount(),psize,pnum);
        System.out.println(pageResult);
        return pageResult;
    }
    public PageResult getSearchBlogsPage(Integer psize,int pnum,String keyword){
        List<ArticleInfo> l = getListByKeyword(psize,(pnum-1)*psize,keyword);
        List<AdminArticleinfo> ll = getAdminArticleInfo(l);
        System.out.println(ll);
        //System.out.println(l);
        PageResult pageResult = new PageResult(ll,articleMapper.getTotalCount(),psize,pnum);
        System.out.println(pageResult);
        return pageResult;
    }

    public List<AdminArticleinfo> getAdminArticleInfo(List<ArticleInfo> articleInfoList) {
        List<AdminArticleinfo> adminArticleInfoList = new ArrayList<>();
        for (ArticleInfo articleInfo : articleInfoList) {
            AdminArticleinfo adminArticleInfo = new AdminArticleinfo();
            adminArticleInfo.setId(articleInfo.getId());
            adminArticleInfo.setTitle(articleInfo.getTitle());
            int maxLength = Math.min(articleInfo.getContent().length(), 15);
            adminArticleInfo.setContent(articleInfo.getContent().substring(0,maxLength)+"……");
            adminArticleInfo.setCreatetime(articleInfo.getCreatetime());
            adminArticleInfo.setUpdatetime(articleInfo.getUpdatetime());
            adminArticleInfo.setUid(articleInfo.getUid());
            adminArticleInfo.setRcount(articleInfo.getRcount());
            adminArticleInfo.setState(articleInfo.getState());

            // 在这里设置 username
            UserInfo userInfo = userMapper.searchByUid(articleInfo.getUid());
            if(userInfo!=null && userInfo.getId()>0){
                adminArticleInfo.setUsername(userInfo.getUsername());
            }
            adminArticleInfoList.add(adminArticleInfo);
        }
        return adminArticleInfoList;
    }
}
