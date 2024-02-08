package org.example.apps.book.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rival
 * @since 2024-01-20
 */
@Data
@NoArgsConstructor
public class BookSearchResponse {
    private Long id;
    private String title;
}
