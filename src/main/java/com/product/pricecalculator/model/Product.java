package com.product.pricecalculator.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "units_per_carton")
    private Integer unitsPerCarton;

    @Column(name = "price_per_carton")
    private Double pricePerCarton;

    @Column(name = "adding_percentage")
    private Double addingPercentagePerUnit;

    @Column(name = "required_cartons")
    private Integer requiredCartonsForDiscount;

    @Column(name = "discount_percentage")
    private Double discountPercentageForCarton;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public void setUnitsPerCarton(Integer unitsPerCarton) {
        this.unitsPerCarton = unitsPerCarton;
    }

    public Double getPricePerCarton() {
        return pricePerCarton;
    }

    public void setPricePerCarton(Double pricePerCarton) {
        this.pricePerCarton = pricePerCarton;
    }

    public Double getAddingPercentagePerUnit() {
        return addingPercentagePerUnit;
    }

    public void setAddingPercentagePerUnit(Double addingPercentagePerUnit) {
        this.addingPercentagePerUnit = addingPercentagePerUnit;
    }

    public Integer getRequiredCartonsForDiscount() {
        return requiredCartonsForDiscount;
    }

    public void setRequiredCartonsForDiscount(Integer requiredCartonsForDiscount) {
        this.requiredCartonsForDiscount = requiredCartonsForDiscount;
    }

    public Double getDiscountPercentageForCarton() {
        return discountPercentageForCarton;
    }

    public void setDiscountPercentageForCarton(Double discountPercentageForCarton) {
        this.discountPercentageForCarton = discountPercentageForCarton;
    }
}
