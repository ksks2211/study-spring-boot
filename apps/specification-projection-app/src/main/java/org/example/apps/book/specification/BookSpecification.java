package org.example.apps.book.specification;

import jakarta.persistence.criteria.Join;
import org.example.apps.book.entity.Book;
import org.example.apps.book.entity.BookKeyword;
import org.example.apps.book.entity.Keyword;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author rival
 * @since 2024-01-04
 */
public class BookSpecification {
    public static Specification<Book> titleStartsWith(String title){
        return (root,query, criteriaBuilder)->
            title == null ? null : criteriaBuilder.like(root.get("title"),title+"%");
    }
    public static Specification<Book> haveTag(String value){
        return (root,query, criteriaBuilder)->{
            Join<Book, BookKeyword> bookJoin = root.join("bookKeywords");
            Join<BookKeyword, Keyword> keywordJoin = bookJoin.join("keyword");
            return criteriaBuilder.equal(keywordJoin.get("value"),value);
        };
    }
}
