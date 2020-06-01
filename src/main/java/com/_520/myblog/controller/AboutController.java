package com._520.myblog.controller;

import com._520.myblog.config.UserMessageConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Werdio丶
 * @since 2020-06-01 09:36:15
 */
@Slf4j
@Controller
public class AboutController {

    @Autowired
    private UserMessageConfig config;

    @GetMapping("/about")
    public String about(Model model){
        log.info("关于我");
        log.info("userConfig = {}", config);
        model.addAttribute("user", config);
        return "about";
    }
}
