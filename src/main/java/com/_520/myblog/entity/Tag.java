package com._520.myblog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签
 */
@Data
public class Tag  implements Serializable {

    private Long id;
    // 标签名称
    private String name;

// --------------------------------------------------------

    // 一个标签可以对应多篇博客
    private List<Blog> blogs = new ArrayList<>();

}
