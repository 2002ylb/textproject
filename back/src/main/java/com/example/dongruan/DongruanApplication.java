package com.example.dongruan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration


@SpringBootApplication

public class DongruanApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongruanApplication.class, args);
    }

}
