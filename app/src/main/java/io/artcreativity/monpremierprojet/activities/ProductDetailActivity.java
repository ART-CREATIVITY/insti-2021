package io.artcreativity.monpremierprojet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.artcreativity.monpremierprojet.R;
import io.artcreativity.monpremierprojet.entities.Product;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Product product = (Product) getIntent().getSerializableExtra("MY_PROD");
        Log.e("TAG", "onCreate: " + product);
    }
}