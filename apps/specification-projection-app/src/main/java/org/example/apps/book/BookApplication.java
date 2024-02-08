package org.example.apps.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2023-12-19
 */

@SpringBootApplication(scanBasePackages = {"org.example.utils.common","org.example.apps.book"})
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
