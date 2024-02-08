package org.example.apps.cachecookie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2024-01-20
 */


@RestController
@RequestMapping("cache")
@RequiredArgsConstructor
public class CacheController {

    private final CacheUserService cacheUserService;


    @GetMapping("/user")
    public User getUser() {
        Long id = 1L;
        return cacheUserService.getUser(id);
    }


    @DeleteMapping("/user")
    public void deleteUser() {
        Long id=1L;
        cacheUserService.deleteUser(id);
    }
}
