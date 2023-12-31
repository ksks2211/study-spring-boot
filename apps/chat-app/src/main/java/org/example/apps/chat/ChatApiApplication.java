package org.example.apps.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rival
 * @since 2023-09-30
 */
@SpringBootApplication(scanBasePackages = {"org.example.utils.common","org.example.apps.chat"})
public class ChatApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApiApplication.class,args);
    }
}
