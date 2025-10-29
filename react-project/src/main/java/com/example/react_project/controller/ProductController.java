package com.example.react_project.controller;

public class ProductController {package com.example.react_project.controller;

import com.example.react_project.model.Product;
import com.example.react_project.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Fetch all products
    @GetMapping
    public Flux<Product> allProducts() {
        return service.getAll();
    }

    // Fetch single product by ID
    @GetMapping("/{id}")
    public Mono<Product> productById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // Stream products (optional)
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> streamProducts() {
        return service.getAll().delayElements(java.time.Duration.ofSeconds(1));
    }
}



}
