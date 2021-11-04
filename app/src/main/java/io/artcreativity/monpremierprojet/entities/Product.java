package io.artcreativity.monpremierprojet.entities;

import java.io.Serializable;

public class Product implements Serializable {
    public String name;
    public String description;
    public double price;
    public double quantityInStock;
    public double alertQuantity;

    public Product() {
    }

    public Product(String name, String description, double price, double quantityInStock, double alertQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.alertQuantity = alertQuantity;
    }

    @Override
    public String toString() {
        return name + " (" + price + "F CFA)";
    }
}
