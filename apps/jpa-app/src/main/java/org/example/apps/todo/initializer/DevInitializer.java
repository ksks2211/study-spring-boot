package org.example.apps.todo.initializer;

import lombok.RequiredArgsConstructor;
import org.example.apps.todo.entity.Product;
import org.example.apps.todo.repository.ProductRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author rival
 * @since 2023-11-06
 */
@Component
@RequiredArgsConstructor
public class DevInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final ProductRepository productRepository;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        List<Product> products = IntStream.range(0, 20).mapToObj(i -> Product.builder()
            .name("Product" + i)
            .price(100.0 * i)
            .build()).toList();
        productRepository.saveAll(products);
    }
}
