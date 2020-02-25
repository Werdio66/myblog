package com._520.myblog.mapper;

import com._520.myblog.entity.Blog;
import com._520.myblog.entity.Comment;
import com._520.myblog.entity.Type;
import com._520.myblog.entity.User;
import com._520.myblog.po.Condition;
import com._520.myblog.service.BlogService;
import com._520.myblog.service.CommentService;
import com._520.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Crud {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Test
    void select() {
         /*PageHelper.startPage(2, 5);
        PageInfo<Type> pageInfo = typeService.listTypePage();
        List<Type> list = pageInfo.getList();
        list.forEach(System.out::println);*/

        List<Comment> comments = commentService.queryAllByBlogId(2L);
        comments.forEach(System.out::println);
    }

    @Test
    void add(){

    }

    @Test
    void queryByCondition(){
        Condition condition = new Condition();
        condition.setTypeId(1L);
        PageInfo<Blog> pageInfo = blogService.listBlogPage(condition);
        pageInfo.getList().forEach(System.out::println);
    }
}
