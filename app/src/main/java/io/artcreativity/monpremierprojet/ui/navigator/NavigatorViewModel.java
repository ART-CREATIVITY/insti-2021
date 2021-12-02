package io.artcreativity.monpremierprojet.ui.navigator;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.artcreativity.monpremierprojet.dao.DataBaseRoom;
import io.artcreativity.monpremierprojet.dao.ProductDao;
import io.artcreativity.monpremierprojet.dao.ProductRoomDao;
import io.artcreativity.monpremierprojet.entities.Product;
import io.artcreativity.monpremierprojet.webservices.ProductWebService;

public class NavigatorViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private ProductDao productDao;
    private ProductRoomDao productRoomDao;
    public MutableLiveData<List<Product>> mutableLiveData = new MutableLiveData<>();

    public NavigatorViewModel(Application application) {
        super(application);
        this.productDao = new ProductDao(application);
        this.productRoomDao = DataBaseRoom.getInstance(application).productRoomDao();
    }

    public void loadProduct() {
        Log.d("TAG", "loadProduct: ");
        new Thread(() -> {
            ProductWebService productWebService = new ProductWebService();
            mutableLiveData.postValue(productWebService.getProducts());
        }).start();
    }
}