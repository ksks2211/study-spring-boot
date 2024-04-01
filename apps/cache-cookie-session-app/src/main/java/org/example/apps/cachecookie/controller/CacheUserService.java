package org.example.apps.cachecookie.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rival
 * @since 2024-01-20
 */
@Service
@RequiredArgsConstructor
public class CacheUserService {

    private final CacheManager cacheManager;
    private final RedisTemplate<Object, Object> redisTemplate;


    @Value("${spring.data.redis.cache-prefix}")
    private String CACHE_PREFIX;

    private String getCacheKey(String cacheName, Long id){
        return CACHE_PREFIX+cacheName+"::"+id.toString();
    }

    public void deleteCache(String cacheName, Long id){
        String key = getCacheKey(cacheName, id);
        redisTemplate.delete(key);
    }

    public void deleteCaches(String cacheName, List<Long> ids){
        List<String> keys = ids.stream().map(id -> getCacheKey(cacheName, id)).toList();
        redisTemplate.delete(keys);
    }



    @Nullable
    public <T> T getFromCache(String cacheName,Long id, Class<T> type){
        Cache cache = cacheManager.getCache(cacheName);
        T value = cache.get(id, type);
        return value;
    }

    @Cacheable(cacheNames = "users",key="#id")
    public User getUser(Long id){
        User user = new User();
        user.setId(1L);
        user.setUsername("app-user");
        return user;
    }




//    @CacheEvict(cacheNames = "posts",key="#id")
    public void deleteUser(Long id){
        redisTemplate.delete("spring:cache:users::1");
    }


}
