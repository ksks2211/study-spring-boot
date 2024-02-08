package org.example.apps.aop.dto;

import lombok.Data;

import java.util.List;

/**
 * @author rival
 * @since 2024-02-07
 */

@Data
public class SearchRequest {

    private List<String> words;
}
