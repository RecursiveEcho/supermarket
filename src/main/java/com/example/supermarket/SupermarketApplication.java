package com.example.supermarket;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
        info = @Info(
                title = "商品管理系统API", // 文档标题
                version = "1.0.0",       // 版本
                description = "基于Spring Boot 3 + Knife4j 的商品增删改查接口文档"
        )
)
@SpringBootApplication
public class SupermarketApplication {


    public static void main(String[] args) {
        SpringApplication.run(SupermarketApplication.class, args);
    }

}
