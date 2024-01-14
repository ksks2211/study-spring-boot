package org.example.apps.cachecookie.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2024-01-13
 */
@RestController
@RequestMapping("session")
public class SessionController {

    @GetMapping("/user")
    public String getSessionId(HttpSession httpSession){
        httpSession.setAttribute("hello","world");
        return httpSession.getId();
    }
}