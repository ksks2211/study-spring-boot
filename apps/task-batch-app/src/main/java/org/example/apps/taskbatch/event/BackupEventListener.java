package org.example.apps.taskbatch.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.taskbatch.SearchService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author rival
 * @since 2024-02-19
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class BackupEventListener {

    private final SearchService searchService;

    @EventListener
    public void handleBackupEvent(BackupEvent event){
        log.info("Event detected - handle event");
        CompletableFuture<Long> future = searchService.save(event.getQuery());
        future.thenAccept(id->log.info("event handling is done and Id : {}",id));
    }

}
