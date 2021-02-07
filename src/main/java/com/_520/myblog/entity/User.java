package com._520.myblog.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *  用户
 */
@Data
public class User implements Serializable {

    private Long id;
    // 昵称
    private String nickName;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 类型
    private String type;
    // 邮箱
    private String mail;
    // 头像
    private String avatar;
    // 创建时间
    private LocalDate creatTime;
    // 修改时间
    private LocalDate updateTime;
}
