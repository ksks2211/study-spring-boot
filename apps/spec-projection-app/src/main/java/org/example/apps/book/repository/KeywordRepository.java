package org.example.apps.book.repository;

import org.example.apps.book.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author rival
 * @since 2024-01-03
 */
public interface KeywordRepository extends JpaRepository<Keyword,Long> {

    Optional<Keyword> findByValue(String value);
}
