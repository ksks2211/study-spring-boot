package org.example.apps.aop.service;

import org.example.apps.aop.dto.SearchRequest;
import org.springframework.stereotype.Service;

/**
 * @author rival
 * @since 2024-02-07
 */
@Service
public class SearchService {
    public SearchRequest search(SearchRequest req){
        return  req;
    }
}
