package org.example.apps.book.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rival
 * @since 2024-01-03
 */

@Data
public class BookResponse {
    private Long id;
    private String title;
    private List<String> keywordValues = new ArrayList<>();
}
