package org.example.apps.taskbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"org.example.apps.taskbatch"})
public class BatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class,args);
    }
}
