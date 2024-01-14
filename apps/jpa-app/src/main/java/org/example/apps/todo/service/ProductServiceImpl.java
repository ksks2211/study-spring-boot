package org.example.apps.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.apps.todo.entity.Product;
import org.example.apps.todo.repository.ProductRepository;
import org.example.apps.todo.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author rival
 * @since 2023-11-06
 */
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    @Override
    public Page<Product> findByCriteria(String name, String prefix, Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<Product> spec = Specification.where(null);

        if(name!=null){
            spec = spec.and(ProductSpecification.hasName(name));
        }
        if(prefix!=null){
            spec = spec.and(ProductSpecification.hasNameStartsWith(prefix));
        }
        if(minPrice!=null && maxPrice !=null){
            spec = spec.and(ProductSpecification.priceBetween(minPrice, maxPrice));
        }

        return productRepository.findAll(spec,pageable);
    }
}
