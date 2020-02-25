package com._520.myblog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, String password, HttpSession session){
        log.info("用户名：{}，密码：{}", username, password);
        session.setAttribute("loginUser", username);
        return "redirect:/main.html";
    }

}
