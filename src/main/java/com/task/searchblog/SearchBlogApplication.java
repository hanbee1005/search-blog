package com.task.searchblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SearchBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchBlogApplication.class, args);
    }

}
