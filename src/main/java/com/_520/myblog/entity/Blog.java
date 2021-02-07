package com._520.myblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 博客类
 */
@Data
public class Blog implements Serializable {

    private Long id;
    // 博客标题
    private String title;
    // 博客内容
    private String content;
    // 博客页面的图片
    private String firstPicture;
    // 标志   原创，转载等
    private String flag;
    // 访问数量
    private Integer viewsCount;
    // 是否开启赞赏
    private boolean appreciation;
    // 是否推荐
    private boolean commentabled;
    // 是否开启版权（转载声明）
    private boolean shareStatement;
    // 发布状态
    private boolean published;
    // 是否开启评论
    private boolean recomend;
    // 创建时间
    private LocalDate creatTime;
    // 修改时间
    private LocalDate updateTime;
    // 博客描述
    private String description;

    // --------------------------------------------------------------------

    // 一篇博客对应一个类型
    private Type type;
    // 一篇博客可以有多个标签
    private List<Tag> tags = new ArrayList<>();
    // 一篇博客可以有多条评论
    private List<Comment> comments = new ArrayList<>();
    // 一篇博客由一个人发布
    private User user;

    private Long typeId;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", viewsCount=" + viewsCount +
                ", appreciation=" + appreciation +
                ", commentabled=" + commentabled +
                ", shareStatement=" + shareStatement +
                ", published=" + published +
                ", recomend=" + recomend +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", tags=" + tags +
                ", comments=" + comments +
                ", user=" + user +
                ", typeId=" + typeId +
                '}';
    }
}
