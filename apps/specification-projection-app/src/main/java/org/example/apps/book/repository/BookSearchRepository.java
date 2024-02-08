package org.example.apps.book.repository;

import org.example.apps.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author rival
 * @since 2024-01-20
 */
public interface BookSearchRepository {


    Page<BookSearchProjection> searchAllBooks(Specification<Book> spec, Pageable pageable);



}
