package com._520.myblog.controller;

import com._520.myblog.entity.Blog;
import com._520.myblog.entity.Tag;
import com._520.myblog.entity.Type;
import com._520.myblog.service.BlogService;
import com._520.myblog.service.TagService;
import com._520.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    /**
     *  跳转到前端页面
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "6")Integer pageSize,
                        Model model){

        log.info("博客前端首页：");
        log.info("当前页：{}", pageNum);

        PageHelper.startPage(pageNum, pageSize);
        // 查询博客
        PageInfo<Blog> pageInfo = blogService.listBlogPage(null);
        log.info("查询的博客：{}", pageInfo.getList());
        // 查询分类
        List<Type> types = typeService.queryAllSortByBlogSize(1, 6);
        log.info("查询的分类：{}", types);

        // 查询标签
        List<Tag> tags = tagService.queryAllSortByBlogSize(1, 8);
        log.info("查询的标签：{}", tags);

        // 查询最新推荐的博客
        List<Blog> blogs = blogService.selectRecommend(1, 6);
        log.info("推荐的博客：{}", blogs);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
        model.addAttribute("blogs", blogs);
        return "index";
    }

    // 局部刷新页面
    @PostMapping("/loadData")
    public String loadData(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "6")Integer pageSize,
                        Model model){

        log.info("刷新博客首页：");
        log.info("当前页：{}", pageNum);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Blog> pageInfo = blogService.listBlogPage(null);

        model.addAttribute("pageInfo", pageInfo);
        return "index :: blog_content";
    }
}
