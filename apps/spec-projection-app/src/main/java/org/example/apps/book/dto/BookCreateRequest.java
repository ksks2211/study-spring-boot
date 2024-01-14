package org.example.apps.book.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rival
 * @since 2024-01-03
 */

@Data
public class BookCreateRequest {
    private String title;
    private Set<String> keywordValues = new HashSet<>();
}
