package com._520.myblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Werdio丶
 * @since 2020-06-01 09:31:22
 */
@Data
@Component
@ConfigurationProperties(prefix = "user")
public class UserMessageConfig {

    /**
     * 用户信息
     */
    private String content;

    /**
     * 标签
     */
    private List<String> tagList;

    /**
     * 学习的语言，java等
     */
    private List<String> languageList;
}
