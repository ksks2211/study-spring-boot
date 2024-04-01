package org.example.apps.taskbatch.event;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

/**
 * @author rival
 * @since 2024-02-19
 */
@Getter
@Slf4j
public class BackupEvent extends ApplicationEvent {

    private final String query;

    public BackupEvent(Object source, String query) {
        super(source);
        this.query = query;
        log.info("Event Created");

    }
}
