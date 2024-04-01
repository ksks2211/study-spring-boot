package org.example.apps.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author rival
 * @since 2024-04-01
 */
@Configuration
@ConfigurationProperties(prefix = "mail")
@Getter
@Setter
public class MailProperties {

    private String host;
    private int port;
    private String username;
    private String password;
}
