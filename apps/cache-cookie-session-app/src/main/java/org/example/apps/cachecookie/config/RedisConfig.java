package org.example.apps.cachecookie.config;

import lombok.RequiredArgsConstructor;
import org.example.apps.cachecookie.dto.PostResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author rival
 * @since 2024-02-15
 */

@Configuration
@RequiredArgsConstructor
public class RedisConfig {


    @Bean
    public RedisSerializer<Object> redisSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        return new StringRedisSerializer();
    }

    @Bean
    public RedisTemplate<String, PostResponse> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, PostResponse> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use StringRedisSerializer for the keys
        template.setKeySerializer(stringRedisSerializer());

        // Use GenericJackson2JsonRedisSerializer for the values
        template.setValueSerializer(redisSerializer());

        // Optionally, configure serializers for hash keys and values
        template.setHashKeySerializer(stringRedisSerializer());
        template.setHashValueSerializer(redisSerializer());

        return template;
    }
}
