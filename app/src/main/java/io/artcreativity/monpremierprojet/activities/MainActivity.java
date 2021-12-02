package io.artcreativity.monpremierprojet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import io.artcreativity.monpremierprojet.R;
import io.artcreativity.monpremierprojet.entities.Product;
import io.artcreativity.monpremierprojet.webservices.ProductWebService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = MainActivity.class.getCanonicalName();

    private TextInputEditText designationEditText;
    private TextInputEditText descriptionEditText;
    private TextInputEditText priceEditText;
    private TextInputEditText quantityInStockEditText;
    private TextInputEditText alertQuantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        designationEditText = findViewById(R.id.name);
        descriptionEditText = findViewById(R.id.description);
        priceEditText = findViewById(R.id.price);
        quantityInStockEditText = findViewById(R.id.quantity_in_stock);
        alertQuantityEditText = findViewById(R.id.alert_quantity);
//        findViewById(R.id.my_btn).setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                saveProduct(view);
//            }
//        });
        findViewById(R.id.my_btn).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void saveProduct(View view) {
        Log.d(TAG, "saveProduct: ");
        Product product = new Product();
        product.name = designationEditText.getText().toString();
        product.description = descriptionEditText.getText().toString();
        product.price = Double.parseDouble(priceEditText.getText().toString());
        product.quantityInStock = Double.parseDouble(quantityInStockEditText.getText().toString());
        product.alertQuantity = Double.parseDouble(alertQuantityEditText.getText().toString());
        Log.e(TAG, "saveProduct: " + product);
        Toast.makeText(getApplicationContext(), "J'ai clique", Toast.LENGTH_SHORT).show();

        new Thread(
                ()->{
                    ProductWebService productWebService = new ProductWebService();
                    Product save = productWebService.createProduct(product);
//                    System.out.println(save);
                    System.out.println("save :: " + save);
                    runOnUiThread(()->{
//                        Intent intent = getIntent();
//                        intent.putExtra("MY_PROD", save);
//                        setResult(Activity.RESULT_OK, intent);
//                        finish();
                    });
                }
        ).start();

//        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        saveProduct(view);
    }
}