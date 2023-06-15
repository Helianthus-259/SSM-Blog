package com.example.demo.controller;

import com.example.demo.common.AjaxResult;
import com.example.demo.common.Constant;
import com.example.demo.common.SessionUtil;
import com.example.demo.model.ArticleInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/art")
public class ArticleController {

    @Autowired
    private ArticleService articleService;



    @RequestMapping("/mylist")
    public List<ArticleInfo> initMyList(HttpServletRequest request){
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if(userInfo != null){
            return articleService.getMyList(userInfo.getId());
        }
        return null;
    }


    @RequestMapping("/count")
    public List<ArticleInfo> initcount(Integer aid){
        if(aid!=null && aid>0){
            return articleService.getMyList(aid);
        }
        return null;
    }

    @RequestMapping("/detail")
    public Object getDetail(Integer aid){
        if(aid!=null&& aid >0){
            return AjaxResult.success(articleService.getDetail(aid));
        }
        return AjaxResult.fail(-1,"查询失败");
    }

    @RequestMapping("/detailbyid")
    public Object getDetailById(Integer aid,HttpServletRequest request){
        if(aid!=null&& aid >0){
            ArticleInfo articleInfo = articleService.getDetail(aid);

            UserInfo userInfo = SessionUtil.getLoginUser(request);
            if(userInfo != null && articleInfo != null && userInfo.getId()==articleInfo.getUid()){
                return AjaxResult.success(articleInfo);
            }
        }
        return AjaxResult.fail(-1,"查询失败");
    }

    @RequestMapping("/update")
    public int update(Integer aid,String title,String content,HttpServletRequest request){
        //todo:非空校验
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if(userInfo!= null && userInfo.getId()>0){
            return articleService.update(aid,userInfo.getId(),title,content);
        }
        return 0;
    }

    @RequestMapping("/list")
    public List<ArticleInfo> getList(Integer pindex,Integer psize){
        if(pindex == null || psize == null){
            return null;
        }
        int offset = (pindex-1)*psize;
        return articleService.getList(psize,offset);
    }

    @RequestMapping("/listByKeyword")
    public List<ArticleInfo> getListByKeyword(Integer pindex,Integer psize,String keyword){
        if(pindex == null || psize == null||keyword==""){
            return null;
        }
        int offset = (pindex-1)*psize;
        return articleService.getListByKeyword(psize,offset,keyword);
    }

    @RequestMapping("/totalpage")
    public Integer getTotalCount(Integer psize){
        System.out.println(psize);
        if(psize!=null){
            int totalCount = articleService.getTotalCount();
            return (int) Math.ceil(totalCount*1.0/psize);
        }

        return null;
    }

    @RequestMapping("/totalpageByKeyword")
    public Integer getTotalCountBykeyword(Integer psize,String keyword){
        System.out.println(psize);
        System.out.println(keyword);
        if(psize!=null&&keyword!=null){
            try {
                String decodedStr = URLDecoder.decode(keyword, StandardCharsets.UTF_8.name());
                System.out.println(decodedStr); // 解码后的字符串
                int totalCount = articleService.getTotalCountBykeyword(decodedStr);
                return (int) Math.ceil(totalCount*1.0/psize);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @RequestMapping("/add")
    public int add(String title,String content,HttpServletRequest request){
        //todo:非空校验
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        System.out.println(title);
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if(userInfo!= null && userInfo.getId()>0){
            return articleService.add(userInfo.getId(),title,content,new Date());
        }
        return 0;
    }

    @RequestMapping("/delArc")
    public Object Deteleart(Integer aid){
        System.out.println(aid);
        if(aid!=null&& aid >0){
            if(!articleService.deleteArc(aid)){
                return AjaxResult.fail(-1,"删除失败");
            }
            ArticleInfo arc = articleService.getDetail(aid);
            if(arc == null){
                return AjaxResult.success(1);
            }
            return AjaxResult.fail(-1,"删除失败");
        }
        return AjaxResult.fail(-1,"删除失败");
    }

}
