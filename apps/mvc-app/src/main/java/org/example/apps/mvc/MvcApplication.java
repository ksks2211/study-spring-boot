package org.example.apps.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2023-10-23
 */
@SpringBootApplication(scanBasePackages = {"org.example.utils.common","org.example.apps.mvc"})
public class MvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class,args);
    }
}
