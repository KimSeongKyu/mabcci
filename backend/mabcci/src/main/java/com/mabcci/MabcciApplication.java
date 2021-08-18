package com.mabcci;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class MabcciApplication {

    public static void main(String[] args) {
        SpringApplication.run(MabcciApplication.class, args);
    }

}
