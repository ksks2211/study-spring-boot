package org.example.apps.aop.controller;

import lombok.RequiredArgsConstructor;
import org.example.apps.aop.dto.SearchRequest;
import org.example.apps.aop.service.SearchService;
import org.springframework.web.bind.annotation.*;

/**
 * @author rival
 * @since 2024-02-07
 */

@RestController
@RequestMapping("search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;



    @PostMapping("")
    public SearchRequest filterBadWords(@RequestBody SearchRequest request){
        return searchService.search(request);
    }
}
