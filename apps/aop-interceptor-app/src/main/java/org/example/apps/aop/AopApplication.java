package org.example.apps.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2023-10-23
 */
@SpringBootApplication(scanBasePackages = {"org.example.utils.common", "org.example.apps.aop"})
public class AopApplication {
    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class,args);
    }
}
