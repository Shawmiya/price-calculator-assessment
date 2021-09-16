package com.product.pricecalculator.service;

import com.product.pricecalculator.model.Order;
import com.product.pricecalculator.model.Price;
import com.product.pricecalculator.model.Product;
import com.product.pricecalculator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    public List<Price> unitBasedPriceList() {
        int noOfUnitsToShow = 50;
        List<Product> productList = productRepository.findAll();
        List<Price> unitBasedPriceListOfProducts = new ArrayList<>();

        productList.forEach(product -> {
            double[] pricesOfUnits = new double[noOfUnitsToShow];
            Price unitBasedPricesForProduct = new Price();

            double addedPriceForSingleUnit = (product.getPricePerCarton() + (product.getPricePerCarton() * product.getAddingPercentagePerUnit())) / product.getUnitsPerCarton();
            double discountPriceForCarton = (product.getPricePerCarton() - (product.getPricePerCarton() * product.getDiscountPercentageForCarton()));

            int currentUnits = 0;
            for (int unitIndex = 0; unitIndex < noOfUnitsToShow; unitIndex++) {
                currentUnits++;
                pricesOfUnits[unitIndex] = unitsPriceCalculator(product, currentUnits, addedPriceForSingleUnit, discountPriceForCarton);
            }
            unitBasedPricesForProduct.setProductName(product.getProductName());
            unitBasedPricesForProduct.setPrice(pricesOfUnits);
            unitBasedPriceListOfProducts.add(unitBasedPricesForProduct);
        });
        return unitBasedPriceListOfProducts;
    }

    public Double ordersPriceCalculator(List<Order> orders) {
        double totalAmount = 0;
        ArrayList<Double> pricesForOrders = new ArrayList<>();

        orders.forEach(order -> {
            double priceOfCurrentOrder = 0;
            Product currentProduct = productRepository.findByProductNameEquals(order.getName());
            double addedPriceForSingleUnit = (currentProduct.getPricePerCarton() + (currentProduct.getPricePerCarton() * currentProduct.getAddingPercentagePerUnit())) / currentProduct.getUnitsPerCarton();
            double discountPriceForCarton = (currentProduct.getPricePerCarton() - (currentProduct.getPricePerCarton() * currentProduct.getDiscountPercentageForCarton()));
            int quantity = Integer.parseInt(order.getCount());

            if (order.getType().equals("Cartons")) {
                int convertedAsUnits = currentProduct.getUnitsPerCarton() * quantity;
                pricesForOrders.add(unitsPriceCalculator(currentProduct, convertedAsUnits, addedPriceForSingleUnit, discountPriceForCarton));
            } else {
                pricesForOrders.add(unitsPriceCalculator(currentProduct, quantity, addedPriceForSingleUnit, discountPriceForCarton));
            }
        });
        for(Double price : pricesForOrders)
            totalAmount += price;

        return totalAmount;
    }

    public double unitsPriceCalculator(Product currentProduct, int currentUnits, double addedPriceForSingleUnit, double discountPriceForCarton) {
        double totalPriceOfCurrentUnits, totalPriceForSingleUnits = 0, totalPriceForCarton = 0;
        int noOfUnits = currentUnits % currentProduct.getUnitsPerCarton();
        int noOfCartons = currentUnits / currentProduct.getUnitsPerCarton();

        if (noOfUnits != 0)
            totalPriceForSingleUnits = addedPriceForSingleUnit * noOfUnits;

        if (noOfCartons != 0) {
            if (noOfCartons < currentProduct.getRequiredCartonsForDiscount()) {
                totalPriceForCarton = currentProduct.getPricePerCarton() * noOfCartons;
            } else {
                totalPriceForCarton = discountPriceForCarton * noOfCartons;
            }
        }
        totalPriceOfCurrentUnits = totalPriceForSingleUnits + totalPriceForCarton;
        return totalPriceOfCurrentUnits;
    }
}
