package org.example.apps.book.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rival
 * @since 2024-01-04
 */
@Data
public class BookSearchRequest {
    private String prefix;
    private List<String> keywordValues = new ArrayList<>();
}
