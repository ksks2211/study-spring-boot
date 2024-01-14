package org.example.apps.todo.specification;

import org.example.apps.todo.entity.Product;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author rival
 * @since 2023-11-06
 */
public class ProductSpecification {

    public static Specification<Product> hasName(String name){
        return (root,query,criteriaBuilder)->
            name == null? null : criteriaBuilder.equal(root.get("name"),name);
    }


    public static Specification<Product> hasNameStartsWith(String name){
        return (root,query,criteriaBuilder)->
            name == null? null : criteriaBuilder.like(root.get("name"),name+"%");
    }

    public static Specification<Product> priceBetween(Double minPrice, Double maxPrice){
        return (root, query, criteriaBuilder)->
            minPrice == null && maxPrice == null ? null :
                criteriaBuilder.between(root.get("price"),minPrice!=null? minPrice : 0, maxPrice !=null? maxPrice : Double.MAX_VALUE );
    }
}
