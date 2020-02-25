package com._520.myblog;

import com._520.myblog.po.BaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@SpringBootTest
class MyblogApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(LocalDateTime.now());
    }


    @Test
    void resp() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
    }
}
