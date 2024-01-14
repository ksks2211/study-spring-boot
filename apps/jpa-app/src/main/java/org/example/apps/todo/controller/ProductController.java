package org.example.apps.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.todo.entity.Product;
import org.example.apps.todo.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rival
 * @since 2023-11-06
 */

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public List<Product> getProducts(
        @RequestParam(value="name", required=false) String name,
        @RequestParam(value="prefix", required = false) String prefix,
        @RequestParam(value="price_min", required = false) Double priceMin,
        @RequestParam(value="price_max", required = false) Double priceMax,
        @PageableDefault Pageable pageable
        ){


        Page<Product> page = productService.findByCriteria(name, prefix, priceMin, priceMax, pageable);
        return page.getContent();
    }
}
