package org.example.apps.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author rival
 * @since 2024-01-03
 */

@Entity
@Table(indexes = {
    @Index(name="index_keyword", columnList = "value", unique = true)
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Keyword {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value;


}
