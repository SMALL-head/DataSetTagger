package com.zyc.datasettagger;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan({"com.zyc.datasettagger.mapper", "com.zyc.datasettagger.config.security.mapper"})
public class DataSetTaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataSetTaggerApplication.class, args);
    }
}
