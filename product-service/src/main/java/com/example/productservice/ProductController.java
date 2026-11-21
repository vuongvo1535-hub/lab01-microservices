
package com.example.productservice;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Danh sách 3 sản phẩm mẫu
    private final List<Map<String, Object>> products = List.of(
        Map.of("id", 1, "name", "Mouse", "price", 150000),
        Map.of("id", 2, "name", "Keyboard", "price", 250000),
        Map.of("id", 3, "name", "Monitor", "price", 2500000)
    );

    // API lấy danh sách sản phẩm
    @GetMapping
    public List<Map<String, Object>> getProducts() {
        return products;
    }

    // API tìm sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(
            @PathVariable int id) {

        return products.stream()
            .filter(p -> p.get("id").equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
