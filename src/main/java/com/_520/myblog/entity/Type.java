package com._520.myblog.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 */
@Data
public class Type {

    private Long id;
    // 分类名称
    private String name;

    // 每种分类有多少博客
    private List<Blog> blogs = new ArrayList<>();
}
