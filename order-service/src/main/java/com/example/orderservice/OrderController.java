
package com.example.orderservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RestClient restClient;

    public OrderController(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    @GetMapping
    public List<Map<String, Object>> getOrders() {
        return List.of(
            Map.of("id", 101, "productId", 1, "quantity", 2),
            Map.of("id", 102, "productId", 2, "quantity", 1)
        );
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable int id) {
        return restClient.get()
                .uri("/api/products/{id}", id)
                .retrieve()
                .body(String.class);
    }
}
