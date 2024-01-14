package org.example.apps.mvc.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rival
 * @since 2023-10-24
 */
@Slf4j
public class SessionListener implements HttpSessionListener {
    // A thread-safe set to store the session ids
    private static final ConcurrentHashMap<String, Boolean> activeSessions = new ConcurrentHashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("Session Created : {}", se.getSession().getId());
        // Add session to the set when it's created
        activeSessions.put(se.getSession().getId(), Boolean.TRUE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        log.info("Session Removed : {}", se.getSession().getId());
        // Remove session from the set when it's destroyed
        activeSessions.remove(se.getSession().getId());
    }

    public static ConcurrentHashMap.KeySetView<String, Boolean> getActiveSessions() {
        return activeSessions.keySet();
    }

}
