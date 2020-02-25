package com._520.myblog.controller;

import com._520.myblog.entity.Blog;
import com._520.myblog.entity.Type;
import com._520.myblog.service.BlogService;
import com._520.myblog.service.TypeService;
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
@RequestMapping("/type")
@Controller
public class TypeIndexController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;


    @GetMapping("/index")
    public String index(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "6")Integer pageSize,
                        @RequestParam(value = "id", required = false)Long id,
                        Model model){
        log.info("分类首页：");
        log.info("typeId = {}", id);

        List<Type> types = typeService.queryAllSortByBlogSize();
        log.info("所有的分类：{}", types);

        if (id == null){
            id = types.get(0).getId();
        }
        // 查询所有的博客
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Blog> pageInfo = blogService.selectByTypeId(id);

        log.info("所有的博客：{}", pageInfo.getList());

        model.addAttribute("typeId", id);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        return "types";
    }


    @GetMapping("/loadBlog")
    public String loadBlog(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "5")Integer pageSize,
                           @RequestParam(value = "id", required = false)Long id,
                           Model model){
        log.info("加载分类中的博客：");
        log.info("typeId = {}", id);

        List<Type> types = typeService.queryAllSortByBlogSize();
        log.info("所有的分类：{}", types);

        if (id == null){
            id = types.get(0).getId();
        }
        // 查询所有的博客
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Blog> pageInfo = blogService.selectByTypeId(id);

        log.info("所有的博客：{}", pageInfo.getList());

        model.addAttribute("typeId", id);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        return "types";
    }

}
