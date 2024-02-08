package org.example.apps.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rival
 * @since 2023-12-19
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(indexes =
    {@Index(name="index_title", columnList = "title")}
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<BookKeyword> bookKeywords = new HashSet<>();



    public void addKeyword(Keyword keyword){
        BookKeyword bookKeyword =  BookKeyword.builder().book(this).keyword(keyword).build();
        this.bookKeywords.add(bookKeyword);
    }
}
