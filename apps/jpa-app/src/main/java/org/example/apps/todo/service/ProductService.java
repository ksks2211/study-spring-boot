package org.example.apps.todo.service;

import org.example.apps.todo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author rival
 * @since 2023-11-06
 */
@Service
public interface ProductService {
    Page<Product> findByCriteria(String name, String prefix, Double minPrice, Double maxPrice, Pageable pageable);
}
