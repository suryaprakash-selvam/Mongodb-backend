package com.EFMS.Entity2;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
public class ProductDetails {
    private int productId;
    private String productName;
    private List<Spec> specs;
    private String suggestion;
    private double price;
    private int imageId;

    public ProductDetails(int productId, String productName, List<Spec> specs, String suggestion, double price, int imageId) {
        this.productId = productId;
        this.productName = productName;
        this.specs = specs;
        this.suggestion = suggestion;
        this.price = price;
        this.imageId = imageId;
    }

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = specs;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

class Spec{
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
