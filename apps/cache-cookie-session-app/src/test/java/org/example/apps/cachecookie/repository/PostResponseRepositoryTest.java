package org.example.apps.cachecookie.repository;

import org.example.apps.cachecookie.config.RedisConfig;
import org.example.apps.cachecookie.dto.PostResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author rival
 * @since 2024-02-15
 */

@DataRedisTest
@ExtendWith(SpringExtension.class)
@Import(RedisConfig.class)
class PostResponseRepositoryTest {

    @Autowired
    private PostResponseRepository postResponseRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String,PostResponse> redisTemplate;


    @Test
    public void test_post_repository(){

        PostResponse entity = PostResponse.builder()
            .id("1")
            .title("Hello World")
            .content("Nice to meet U")
            .build();

        postResponseRepository.save(entity);
        Optional<PostResponse> foundEntity = postResponseRepository.findById("1");
        assertTrue(foundEntity.isPresent());
    }



    @Test
    public void test_string_redis_template(){
        String key = "testKey";
        String value = "testValue";

        // create
        stringRedisTemplate.opsForValue().set(key,value);


        String retrievedValue = stringRedisTemplate.opsForValue().get(key);
        assertEquals(value, retrievedValue);

    }



    // Simple Key-Value
    @Test
    public void test_redis_template(){

        String key = "PostResponse:1";

        String title = "Hello World";

        PostResponse entity = PostResponse.builder()
            .id("1")
            .title(title)
            .content("Nice to meet U")
            .build();

        redisTemplate.opsForValue().set(key,entity);
        PostResponse postResponse =  redisTemplate.opsForValue().get(key);


        assertNotNull(postResponse);
        assertEquals(title, postResponse.getTitle());

    }





}