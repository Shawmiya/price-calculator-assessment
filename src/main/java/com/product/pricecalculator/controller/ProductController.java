package com.product.pricecalculator.controller;

import com.product.pricecalculator.model.Order;
import com.product.pricecalculator.model.Price;
import com.product.pricecalculator.model.Product;
import com.product.pricecalculator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.allProducts();
    }

    @GetMapping("/price-list")
    public List<Price> getPriceList() {
        return productService.unitBasedPriceList();
    }

    @PostMapping("/price-calculator")
    public Double getTotal(@RequestBody List<Order> orders) {
        return productService.ordersPriceCalculator(orders);
    }
}
