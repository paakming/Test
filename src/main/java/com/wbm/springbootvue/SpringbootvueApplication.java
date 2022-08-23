package com.wbm.springbootvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = "com.wbm.springbootvue.mapper")
public class SpringbootvueApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootvueApplication.class, args);
    }
}
