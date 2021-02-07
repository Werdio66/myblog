package com._520.myblog.mapper;

import com._520.myblog.service.impl.BlogServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Werdio丶
 * @since 2021-02-07 18:55:22
 */
@SpringBootTest
class BlogMapperTest {

    @Autowired
    private BlogServiceImpl blogService;

    @Test
    void selectByIds() {
        Set<Long> ids = new LinkedHashSet<>();
        ids.add(2L);
        ids.add(1L);
        ids.add(3L);
        System.out.println(blogService.selectByIds(ids));
    }
}