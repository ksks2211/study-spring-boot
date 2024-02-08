package org.example.apps.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2024-02-08
 */

@SpringBootApplication(scanBasePackages = {"org.example.apps.elastic"})
public class ElasticApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticApplication.class,args);
    }

}
