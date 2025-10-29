package com.example.react_project.service;

public class ProductService {package com.example.react_project.service;

import com.example.react_project.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product(1, "Guitar", 199.99));
        products.add(new Product(2, "Headphones", 49.99));
        products.add(new Product(3, "Keyboard", 79.99));
    }

    public Flux<Product> getAll() {
        return Flux.fromIterable(products);
    }

    public Mono<Product> getById(Integer id) {
        return Flux.fromIterable(products)
                .filter(p -> p.getId().equals(id))
                .next(); // convert Flux -> Mono
    }
}


}
