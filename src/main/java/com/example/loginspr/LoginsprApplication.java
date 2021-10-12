package com.example.loginspr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.loginspr.mapper")
public class LoginsprApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginsprApplication.class, args);
    }

}
