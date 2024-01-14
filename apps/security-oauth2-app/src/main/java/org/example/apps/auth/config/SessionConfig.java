package org.example.apps.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author rival
 * @since 2023-12-26
 */

@Configuration
@EnableRedisHttpSession
public class SessionConfig {
}
