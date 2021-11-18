package io.artcreativity.monpremierprojet.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "quantityInStock")
    public double quantityInStock;
    @ColumnInfo(name = "alertQuantity")
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
