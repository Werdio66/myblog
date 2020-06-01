package com._520.myblog.controller;

import com._520.myblog.entity.Blog;
import com._520.myblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author Werdio丶
 * @since 2020-06-01 08:17:03
 */
@Slf4j
@Controller
public class ArchiveController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archive")
    public String archive(Model model){
        log.info("归档。。。");
        Map<String, List<Blog>> blogMap = blogService.queryByArchive();
        int count = blogService.queryCount();
        log.info("博客总数：{}", count);
        log.info("blogMap：{}", blogMap);
        model.addAttribute("total", count);
        model.addAttribute("blogMap", blogMap);
        return "archives";
    }
}
