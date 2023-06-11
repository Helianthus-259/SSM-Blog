package com.example.demo.controller.admin;

import com.example.demo.service.ArticleService;
import com.example.demo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import com.example.demo.util.Result;
import com.example.demo.util.ResultGenerator;
import com.example.demo.util.PageQueryUtil;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/blogs")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "blogs");
        return "admin/blog";
    }

    @GetMapping("/blogs/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Integer psize = Integer.parseInt((String) params.get("limit"));
        Integer pnum = Integer.parseInt((String) params.get("page"));
        String keyword = (String) params.get("keyword");
        System.out.println(pnum);
        System.out.println(psize);
        System.out.println(keyword);
        if(keyword != null){
            PageResult pageSearchResult = articleService.getSearchBlogsPage(psize,pnum,keyword);
            return ResultGenerator.genSuccessResult(pageSearchResult);
        }
//        Integer psize = 10;
//        Integer pnum = 1;
        //PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult pageResult = articleService.getBlogsPage(psize,pnum);
        //System.out.println(pageResult);
        return ResultGenerator.genSuccessResult(pageResult);
    }

    @PostMapping("/blogs/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (articleService.deleteArcList(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

    @GetMapping("/blogs/search")
    @ResponseBody
    public Result searchlist(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Integer psize = Integer.parseInt((String) params.get("limit"));
        Integer pnum = Integer.parseInt((String) params.get("page"));
        String keyword = (String) params.get("postData");
//        Integer psize = 10;
//        Integer pnum = 1;
        System.out.println(pnum);
        System.out.println(psize);
        System.out.println(keyword);
        //PageQueryUtil pageUtil = new PageQueryUtil(params);
//        PageResult pageResult = articleService.getBlogsPage(psize,pnum);
//        //System.out.println(pageResult);
//        return ResultGenerator.genSuccessResult(pageResult);
        return null;
    }

}
