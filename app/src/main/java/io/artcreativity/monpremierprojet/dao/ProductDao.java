package io.artcreativity.monpremierprojet.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import io.artcreativity.monpremierprojet.entities.Product;

public class ProductDao {

    private DataBaseHelper dataBaseHelper;

    public ProductDao(Context context){
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    public Product insert(Product product){
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.name);
        contentValues.put("description", product.description);
        contentValues.put("price", product.price);
        contentValues.put("quantityInStock", product.quantityInStock);
        contentValues.put("alertQuantity", product.alertQuantity);
        long id = database.insert(Product.TABLE_NAME, null, contentValues);
        product.id = (int) id;
        return product;
    }

    public Product getOne(int id){
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();
        String[] column = new String[]{"id", "name", "description", "price", "quantityInStock", "alertQuantity"};

        String where = "id=?";
        String[] whereArgs = new String[]{id+""};
        @SuppressLint("Recycle")
        Cursor cursor = database.query(Product.TABLE_NAME, column, where,
                whereArgs, "", "", "");
        if(cursor!=null && cursor.moveToNext()){

            Product product = new Product();
            product.id = cursor.getInt(Math.max(cursor.getColumnIndex("id"), 0));
            product.name = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));
            product.description = cursor.getString(Math.max(cursor.getColumnIndex("description"), 0));
            product.price = cursor.getDouble(Math.max(cursor.getColumnIndex("price"), 0));
            product.quantityInStock = cursor.getInt(Math.max(cursor.getColumnIndex("quantityInStock"), 0));
            product.alertQuantity = cursor.getInt(Math.max(cursor.getColumnIndex("alertQuantity"), 0));
            cursor.close();
            return product;
        }
        return null;
    }

    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();
        String[] column = new String[]{"id", "name", "description", "price", "quantityInStock", "alertQuantity"};

        @SuppressLint("Recycle")
        Cursor cursor = database.query(Product.TABLE_NAME, column, "",
                null, "", "", "");
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                Product product = new Product();
                product.id = cursor.getInt(Math.max(cursor.getColumnIndex("id"), 0));
                product.name = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));
                product.description = cursor.getString(Math.max(cursor.getColumnIndex("description"), 0));
                product.price = cursor.getDouble(Math.max(cursor.getColumnIndex("price"), 0));
                product.quantityInStock = cursor.getInt(Math.max(cursor.getColumnIndex("quantityInStock"), 0));
                product.alertQuantity = cursor.getInt(Math.max(cursor.getColumnIndex("alertQuantity"), 0));

                products.add(product);
            }
            cursor.close();
        }
        return products;
    }

    public Product update(int id, Product product){
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.name);
        contentValues.put("description", product.description);
        contentValues.put("price", product.price);
        contentValues.put("quantityInStock", product.quantityInStock);
        contentValues.put("alertQuantity", product.alertQuantity);

        String where = "id=?";
        String[] whereArgs = new String[]{id+""};

        database.update(Product.TABLE_NAME, contentValues, where, whereArgs);
        product.id = id;
        return product;
    }

    public boolean delete(int id){
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        int rowDelete = database.delete(Product.TABLE_NAME, "id=?", new String[]{""+id});
        return rowDelete>0;
    }
}
