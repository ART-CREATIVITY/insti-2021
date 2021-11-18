package io.artcreativity.monpremierprojet.entities;

import java.io.Serializable;

public class Product implements Serializable {

    public int id;
    public String name;
    public String description;
    public double price;
    public double quantityInStock;
    public double alertQuantity;

    public static final String TABLE_NAME = "products";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, " +
            "name VARCHAR(100), description TEXT, price REAL default 0, quantityInStock INTEGER default 0," +
            "alertQuantity INTEGER default 0 )";

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
