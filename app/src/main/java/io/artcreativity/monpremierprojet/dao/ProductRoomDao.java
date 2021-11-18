package io.artcreativity.monpremierprojet.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.artcreativity.monpremierprojet.entities.Product;

@Dao
public interface ProductRoomDao {
    @Query("SELECT * FROM product")
    List<Product> findAll();

    @Query("SELECT * FROM product WHERE id IN (:userIds)")
    List<Product> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM product WHERE name LIKE :search AND " +
            "description LIKE :search")
    List<Product> findByName(String search);

    @Insert
    void insertAll(Product... products);

    @Insert
    void insert(Product product);

    @Delete
    void delete(Product product);
}
