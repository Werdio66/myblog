package com._520.myblog.controller.admin;

import com._520.myblog.entity.Type;
import com._520.myblog.po.BaseResponse;
import com._520.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/index")
    public String type(@RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "5")Integer pageSize,
                       Model model) {
        log.info("分类首页");
        log.info("当前页：{}", pageNum);
        log.info("每页数量：{}", pageSize);

        // 设置页码和数量 (与线程绑定)
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Type> pageInfo = typeService.listTypePage();
        log.info("pageInfo = {}", pageInfo);
        log.info("查询的分类 = {}", pageInfo.getList());

        model.addAttribute("pageInfo", pageInfo);
        return "admin/type";
    }

    @ResponseBody
    @PostMapping("/add")
    public BaseResponse<?> add(String name){
        log.info("新增分类：{}", name);
        Type type = new Type();
        type.setName(name);
        typeService.insert(type);
        return BaseResponse.ok("ok");
    }

    @ResponseBody
    @GetMapping("/selectById")
    public BaseResponse<?> selectById(Long id){
        log.info("修改分类的 id = {}", id);
        Type type = typeService.queryById(id);
        log.info("修改前的 type 值 = {}", type);
        return BaseResponse.ok("ok", type);
    }

    @ResponseBody
    @PostMapping("/update")
    public BaseResponse<?> update(Type type){
        log.info("修改分类名称：");
        typeService.update(type);
        return BaseResponse.ok("ok");
    }
}
