package org.example.apps.aop.controller;

import org.example.apps.aop.dto.SessionDto;
import org.example.apps.aop.listener.SessionListener;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author rival
 * @since 2023-10-24
 */
@RestController
@RequestMapping("session")
public class SessionController {

    @GetMapping("/mine")
    public SessionDto getSessionInfo(HttpServletRequest request){
        String currentSession = request.getSession().getId();
        ArrayList<String> sessions = new ArrayList<>(SessionListener.getActiveSessions());

        SessionDto sessionDto = new SessionDto();

        sessionDto.setCurrentSession(currentSession);
        sessionDto.setSessions(sessions);


        return sessionDto;
    }

}
