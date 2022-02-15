package com.tester.tester;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.tester.tester.Mapper")
public class TesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesterApplication.class, args);
    }

}
