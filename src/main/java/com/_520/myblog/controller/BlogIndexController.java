package com._520.myblog.controller;

import com._520.myblog.entity.Blog;
import com._520.myblog.entity.Comment;
import com._520.myblog.service.BlogService;
import com._520.myblog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *  前端博客详情
 */
@Slf4j
@RequestMapping("/blog")
@Controller
public class BlogIndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/index")
    public String index(Long id, Model model){
        log.info("博客详情：");

        Blog blog = blogService.queryById2HTML(id);
        log.info("博客：{}", blog);

        List<Comment> comments = commentService.queryAllByBlogId(id);
        log.info("当前博客的评论：{}", comments);

        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
