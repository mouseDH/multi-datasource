package com.demo;

import com.demo.mapper.db1.Demo1Mapper;
import com.demo.mapper.db2.Demo2Mapper;
import com.demo.mapper.db3.Demo3Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: hao.dai
 * @Date: 2019/10/10 17:34
 * @Description:
 */
@SpringBootApplication(scanBasePackages = {"com.demo.config"})
public class DemoApplication implements CommandLineRunner {
    @Autowired
    Demo1Mapper demo1Mapper;

    @Autowired
    Demo2Mapper demo2Mapper;

    @Autowired
    Demo3Mapper demo3Mapper;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println();
        System.out.println(demo1Mapper.select());
        System.out.println(demo2Mapper.select());
        System.out.println(demo3Mapper.select());
        System.out.println();
    }
}
