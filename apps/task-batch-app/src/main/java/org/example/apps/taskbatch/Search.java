package org.example.apps.taskbatch;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author rival
 * @since 2024-02-19
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;

    @Builder.Default
    @Column(updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

}
