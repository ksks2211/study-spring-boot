package org.example.apps.cachecookie.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2024-01-13
 */

@RestController
@RequestMapping("cookie")
public class CookieController {

    private final String cookieName = "testCookie";

    @GetMapping("/send-cookie")
    public ResponseEntity<String> sendCookie(HttpServletResponse res){
        String cookieVale = "testValue";
        Cookie cookie = new Cookie(cookieName, cookieVale);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 1 week
        res.addCookie(cookie);
        return ResponseEntity.ok("Cookie sent successfully");
    }

    @GetMapping("/read-cookie")
    public ResponseEntity<String> readCookie(@CookieValue(name = cookieName, defaultValue = "No cookie") String cookieValue) {
        return ResponseEntity.ok("Cookie Value: " + cookieValue);
    }


    @GetMapping("/remove-cookie")
    public ResponseEntity<?> removeCookie(HttpServletResponse response){
        Cookie cookie = new Cookie(cookieName,null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.ok("Cookie Removed");
    }
}
