package io.artcreativity.monpremierprojet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.MessageFormat;
import java.util.List;

import io.artcreativity.monpremierprojet.R;
import io.artcreativity.monpremierprojet.entities.Product;

public class ProductAdapter extends BaseAdapter {

    final List<Product> products;
    final Context context;

    public ProductAdapter(Context context, List<Product> products) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ConstraintLayout myView = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.regular_product_item, viewGroup, false);
        TextView nameView = myView.findViewById(R.id.name);
        TextView quantityView = myView.findViewById(R.id.quantity_in_stock);
        TextView priceView = myView.findViewById(R.id.price);
        nameView.setText(products.get(i).name);
        quantityView.setText(MessageFormat.format("{0} disponible{1}", products.get(i).quantityInStock, products.get(i).quantityInStock > 1 ? "s" : ""));
        priceView.setText(MessageFormat.format("XOF {0}", products.get(i).price));
        return myView;
    }
}
