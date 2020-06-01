package com._520.myblog.service.impl;

import com._520.myblog.entity.Blog;
import com._520.myblog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Werdio丶
 * @since 2020-05-24 18:18:25
 */
@SpringBootTest
class BlogServiceImplTest {

    @Autowired
    private BlogService blogService;

    @Test
    void listBlogPage() {
        PageHelper.startPage(1, 5);
        PageInfo<Blog> blogPageInfo = blogService.listBlogPage(null);

        System.out.println("是否为首页 ：" + blogPageInfo.isIsFirstPage());
        System.out.println("是否为尾页 ：" + blogPageInfo.isIsLastPage());
        System.out.println("是否有上一页 ：" + blogPageInfo.isHasPreviousPage());
        System.out.println("是否有下一页 ：" + blogPageInfo.isHasNextPage());
        System.out.println(blogPageInfo);

    }

    @Test
    void queryByArchive(){

    }

    @Test
    void queryCount(){
        System.out.println(blogService.queryCount());
    }
}