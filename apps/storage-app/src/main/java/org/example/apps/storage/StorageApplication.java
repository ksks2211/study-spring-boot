package org.example.apps.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2023-09-30
 */
@SpringBootApplication(
    scanBasePackages = {"org.example.utils.common", "org.example.apps.storage"})
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class,args);
    }
}
