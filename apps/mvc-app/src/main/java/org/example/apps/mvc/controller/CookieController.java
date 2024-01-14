package org.example.apps.mvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-12-10
 */
@RestController
@RequestMapping("cookie")
public class CookieController {
    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("my-cookie", "cookie-value");

        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7*24*60*60);

        response.addCookie(cookie);
        return "Cookie is set";
    }

    @GetMapping("/read-cookie")
    public String readCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("my-cookie".equals(cookie.getName())) {
                    return "Your cookie is " + cookie.getName() + "=" + cookie.getValue();
                }
            }
        }
        return "Cookie is empty";
    }
}
