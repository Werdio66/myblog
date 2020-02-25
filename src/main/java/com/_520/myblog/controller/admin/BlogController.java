package com._520.myblog.controller.admin;

import com._520.myblog.entity.Blog;
import com._520.myblog.entity.Tag;
import com._520.myblog.entity.Type;
import com._520.myblog.po.BaseResponse;
import com._520.myblog.po.Condition;
import com._520.myblog.service.BlogService;
import com._520.myblog.service.TagService;
import com._520.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "3")Integer pageSize,
                        Model model){

        log.info("博客首页：");
        log.info("当前页：{}", pageNum);
        log.info("当前页数量：{}", pageSize);

        // 设置当前页和每页数量
        PageHelper.startPage(pageNum, pageSize);

        // 查询博客
        PageInfo<Blog> pageInfo = blogService.listBlogPage(null);
        log.info("博客分页：{}", pageInfo);

        // 查询分类
        List<Type> types = typeService.queryAll(null);
        log.info("查询的分类：{}", types);



        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    @PostMapping("/flushPage")
    public String flushPage(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "3")Integer pageSize,
                            Model model,
                            Condition condition){

        log.info("局部刷新博客：");
        log.info("条件：{}", condition);
        log.info("当前页：{}", pageNum);
        log.info("当前页数量：{}", pageSize);

        // 设置当前页和每页数量
        PageHelper.startPage(pageNum, pageSize);

        // 查询博客
        PageInfo<Blog> pageInfo = blogService.listBlogPage(condition);
        log.info("博客分页：{}", pageInfo);

        // 查询分类
        List<Type> types = typeService.queryAll(null);
        log.info("查询的分类：{}", types);



        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogTable";
    }


    @GetMapping("/input")
    public String input(Model model){
        log.info("发布博客：");

        // 查询分类
        List<Type> types = typeService.queryAll(null);
        log.info("查询的分类：{}", types);
        // 查询标签
        List<Tag> tags = tagService.queryAll(null);
        log.info("查询的标签：{}", tags);

        model.addAttribute("tags", tags);
        model.addAttribute("types", types);
        return "admin/blogs-input";
    }

    @GetMapping("editInput")
    public String editInput(Model model, Long id){
        log.info("修改博客：");

        // 查询要修改的博客
        Blog blog = blogService.queryById(id);
        // 查询分类
        List<Type> types = typeService.queryAll(null);
        log.info("查询的分类：{}", types);
        // 查询标签
        List<Tag> tags = tagService.queryAll(null);
        log.info("查询的标签：{}", tags);

        model.addAttribute("blog", blog);
        model.addAttribute("tags", tags);
        model.addAttribute("types", types);
        return "admin/blogs-update";
    }

    /**
     *  保存博客
     */
    @PostMapping("/save")
    public String save(Blog blog, Long[] tagIds){
        log.info("保存博客：");
        log.info("博客：{}", blog);
        log.info("博客标签 id = {}", Arrays.toString(tagIds));

        blogService.insert(blog, tagIds);

        return "redirect:/admin/blog/index";
    }

    /**
     *  修改博客内容
     */
    @PostMapping("/update")
    public String update(Blog blog, Long[] tagIds){
        log.info("修改博客：");
        log.info("博客：{}", blog);
        log.info("博客标签 id = {}", Arrays.toString(tagIds));

        blogService.update(blog, tagIds);

        return "redirect:/admin/blog/index";
    }
}
