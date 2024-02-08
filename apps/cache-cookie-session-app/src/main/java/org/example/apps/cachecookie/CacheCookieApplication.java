package org.example.apps.cachecookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2023-10-23
 */
@SpringBootApplication(scanBasePackages = {"org.example.utils.common","org.example.apps.cachecookie"})
public class CacheCookieApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheCookieApplication.class,args);
    }
}
