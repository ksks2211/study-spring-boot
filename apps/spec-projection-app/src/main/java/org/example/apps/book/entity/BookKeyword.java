package org.example.apps.book.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author rival
 * @since 2024-01-03
 */

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="keyword_id", referencedColumnName = "id", nullable = false)
    private Keyword keyword;

}
