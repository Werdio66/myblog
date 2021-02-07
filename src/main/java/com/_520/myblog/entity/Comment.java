package com._520.myblog.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 评论
 */
@Data
public class Comment implements Serializable {

    private Long id;
    // 发表评论的人的昵称
    private String nickName;
    // 邮箱
    private String mail;
    // 头像
    private String avatar;
    // 评论内容
    private String content;
    // 创建时间
    private LocalDateTime creatTime;

    private Long parentId;

    private Long blogId;

// ---------------------------------------------------------

    // 一条评论可以有多个子评论
   private List<Comment> childComments = new ArrayList<>();

   // 父评论
   private String parentName;
}
