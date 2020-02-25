package com._520.myblog.controller;

import com._520.myblog.entity.Comment;
import com._520.myblog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/loadData")
    public String loadData(Comment comment, Model model){
        log.info("加载评论内容：");
        log.info("评论：{}", comment);
        log.info("父评论 id = {}", comment.getParentId());
        log.info("当前评论的博客的 id = {}", comment.getBlogId());

        commentService.insert(comment);

        List<Comment> comments = commentService.queryAllByBlogId(comment.getBlogId());
        log.info("当前博客的评论：{}", comments);

        model.addAttribute("comments", comments);
        return "blog :: comment-body";
    }
}
