package org.example.apps.book.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author rival
 * @since 2024-01-20
 */

@Data
@AllArgsConstructor
public class BookSearchProjection {
    private Long id;
    private String title;
}
