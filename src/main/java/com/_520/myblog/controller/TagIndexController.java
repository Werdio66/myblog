package com._520.myblog.controller;

import com._520.myblog.entity.Blog;
import com._520.myblog.entity.Tag;
import com._520.myblog.service.BlogService;
import com._520.myblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/tag")
public class TagIndexController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;


    @GetMapping("/index")
    public String index(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "6") Integer pageSize,
                        @RequestParam(value = "id", required = false)Long id,
                        Model model) {

        log.info("标签首页：");
        log.info("标签 id = {}", id);

        List<Tag> tags = tagService.queryAllSortByBlogSize();
        log.info("所有的标签：{}", tags);

        if (id == null){
            id = tags.get(0).getId();
        }

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Blog> pageInfo = blogService.selectByTagId(id);
        log.info("所有的博客：{}", pageInfo.getList());

        model.addAttribute("tagId", id);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        return "tags";
    }



    @GetMapping("/loadData")
    public String loadData(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "6") Integer pageSize,
                        @RequestParam(value = "id", required = false)Long id,
                        Model model) {

        log.info("标签首页：");
        log.info("标签 id = {}", id);

        List<Tag> tags = tagService.queryAllSortByBlogSize();
        log.info("所有的标签：{}", tags);

        if (id == null){
            id = tags.get(0).getId();
        }

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Blog> pageInfo = blogService.selectByTagId(id);
        log.info("所有的博客：{}", pageInfo.getList());

        model.addAttribute("tagId", id);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        return "tags :: blog_content";
    }
}
