package org.example.apps.book.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.apps.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author rival
 * @since 2024-01-20
 */
public class BookSearchRepositoryImpl implements BookSearchRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<BookSearchProjection> searchAllBooks(Specification<Book> spec, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookSearchProjection> query = cb.createQuery(BookSearchProjection.class);

        // From ...
        Root<Book> root = query.from(Book.class);


        // Select
        query.select(cb.construct(BookSearchProjection.class, root.get("id"), root.get("title")));

        // where
        query.where(spec.toPredicate(root, query, cb));


        TypedQuery<BookSearchProjection> typedQuery = entityManager.createQuery(query);


        // limit, offset
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());


        List<BookSearchProjection> content = typedQuery.getResultList();

        long total = getCountQuery(spec).getSingleResult();

        return new PageImpl<>(content, pageable, total);


    }


    private TypedQuery<Long> getCountQuery(Specification<Book> spec) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);


        // From book
        Root<Book> countRoot = countQuery.from(Book.class);

        // Select count(*)
        countQuery.select(cb.count(countRoot));

        // where
        countQuery.where(spec.toPredicate(countRoot, countQuery, cb));

        return entityManager.createQuery(countQuery);

    }
}
