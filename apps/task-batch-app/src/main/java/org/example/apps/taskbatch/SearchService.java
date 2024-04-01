package org.example.apps.taskbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author rival
 * @since 2024-02-19
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final SearchRepository searchRepository;


    @Async
    public CompletableFuture<Long> save(String query){
        log.info("save method called!!!");

        try {
            Search search = Search.builder().query(query).build();
            Search save = searchRepository.save(search);
            return CompletableFuture.completedFuture(save.getId());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(-1L);
        }
    }
}
