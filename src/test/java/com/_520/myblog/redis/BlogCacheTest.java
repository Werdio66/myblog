package com._520.myblog.redis;

import com._520.myblog.entity.Blog;
import com._520.myblog.service.impl.BlogServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Werdio丶
 * @since 2021-02-07 11:46:58
 */
@SpringBootTest
class BlogCacheTest {

    @Autowired
    private BlogCache blogCache;
    @Autowired
    private BlogServiceImpl blogService;

    @Test
    void incrViewsCount() {
        blogCache.incrViewsCount("viewcount");
    }
    @Test
    void addBlog() {
        blogCache.addBlog1(null);
    }

    @Test
    void getBlog() {
        blogCache.getBlog1("1");
    }
    @Test
    void zsetBlog() {

    }
    @Test
    void zsetGet() {

    }
    @Test
    void zsetincr() {
        Blog blog = blogService.queryById(1L);
    }
}