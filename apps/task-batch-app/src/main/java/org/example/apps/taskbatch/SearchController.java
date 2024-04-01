package org.example.apps.taskbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.taskbatch.event.BackupEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author rival
 * @since 2024-02-19
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Slf4j
public class SearchController {


    private final SearchService searchService;
    private final ApplicationEventPublisher eventPublisher;



    @GetMapping("")
    public SearchQuery searchQuery(@ModelAttribute SearchQuery searchQuery){

        log.info("searchQuery method start");
        CompletableFuture<Long> future = searchService.save(searchQuery.getQuery());
        future.thenAccept(result->log.info("Search History with ID {} created", result));
        log.info("searchQuery method end");

        return searchQuery;
    }


    @GetMapping("/event")
    public SearchQuery searchQueryEvent(@ModelAttribute SearchQuery searchQuery){

        log.info("searchQueryEvent method start");
        BackupEvent event = new BackupEvent(this, searchQuery.getQuery());
        eventPublisher.publishEvent(event);
        log.info("searchQueryEvent method end");

        return searchQuery;
    }
}
