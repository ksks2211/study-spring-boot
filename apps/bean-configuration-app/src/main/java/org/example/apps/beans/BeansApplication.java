package org.example.apps.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2024-04-01
 */
@SpringBootApplication(scanBasePackages = {"org.example.apps.beans","org.example.utils.common"})
public class BeansApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeansApplication.class, args);
    }
}
