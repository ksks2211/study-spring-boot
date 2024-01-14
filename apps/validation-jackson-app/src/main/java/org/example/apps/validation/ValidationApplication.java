package org.example.apps.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2023-10-24
 */

@SpringBootApplication(scanBasePackages = {"org.example.apps.validation"})
public class ValidationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValidationApplication.class,args);
    }
}
