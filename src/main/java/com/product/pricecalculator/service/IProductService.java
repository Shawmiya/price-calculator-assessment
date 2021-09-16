package com.product.pricecalculator.service;

import com.product.pricecalculator.model.Order;
import com.product.pricecalculator.model.Price;
import com.product.pricecalculator.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> allProducts();
    List<Price> unitBasedPriceList();
    Double ordersPriceCalculator(List<Order> orders);
}
