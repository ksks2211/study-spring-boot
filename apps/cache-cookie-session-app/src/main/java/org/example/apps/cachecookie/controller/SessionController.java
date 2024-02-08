package org.example.apps.cachecookie.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author rival
 * @since 2024-01-13
 */
@RestController
@RequestMapping("session")
@RequiredArgsConstructor
public class SessionController {




    @GetMapping("/user")
    public String getSessionId(HttpSession httpSession){
        httpSession.setAttribute("hello","world");
        return httpSession.getId();
    }

    @GetMapping("/delete")
    public String deleteSession(HttpSession httpSession){
        String sessionId = httpSession.getId();

        httpSession.invalidate();
        return "Delete Session : "+sessionId;
    }

    @GetMapping("/id")
    public String getSessionInfo(HttpServletRequest request){
        return request.getSession().getId();
    }
}