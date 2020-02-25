package com._520.myblog.controller.admin;

import com._520.myblog.entity.Tag;
import com._520.myblog.po.BaseResponse;
import com._520.myblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/admin/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/index")
    public String type(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "5")Integer pageSize,
                       Model model) {
        log.info("标签首页");
        log.info("当前页：{}", pageNum);
        log.info("每页数量：{}", pageSize);

        // 设置页码和数量 (与线程绑定)
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Tag> pageInfo = tagService.listTagPage();
        log.info("pageInfo = {}", pageInfo);
        log.info("查询的标签 = {}", pageInfo.getList());

        model.addAttribute("pageInfo", pageInfo);
        return "admin/tag";
    }

    @ResponseBody
    @PostMapping("/add")
    public BaseResponse<?> add(String name){
        log.info("新增标签：{}", name);
        Tag tag = new Tag();
        tag.setName(name);
        tagService.insert(tag);
        return BaseResponse.ok("ok");
    }

    @ResponseBody
    @GetMapping("/selectById")
    public BaseResponse<?> selectById(Long id){
        log.info("修改标签的 id = {}", id);
        Tag tag = tagService.queryById(id);
        log.info("修改前的 tag 值 = {}", tag);
        return BaseResponse.ok("ok", tag);
    }

    @ResponseBody
    @PostMapping("/update")
    public BaseResponse<?> update(Tag tag){
        log.info("修改标签名称：");
        tagService.update(tag);
        return BaseResponse.ok("ok");
    }
}
