package com.example.telikosredisconsumer;

import com.example.telikosredislibrary.EnableCustomCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCustomCaching
public class TelikosRedisConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelikosRedisConsumerApplication.class, args);
    }

}
