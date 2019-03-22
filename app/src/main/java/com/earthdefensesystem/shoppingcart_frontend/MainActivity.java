package com.earthdefensesystem.shoppingcart_frontend;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.earthdefensesystem.shoppingcart_frontend.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout parentLayout;
    Context context;
    Button listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listButton = findViewById(R.id.loadlist);

        parentLayout = findViewById(R.id.parent_layout);
        context = this;

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayList<Product> products = ShoppingDAO.getProductList();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0; i < products.size(); i++) {
                                    TextView textView = new TextView(context);
                                    final Product getProducts = products.get(i);
                                    textView.setText(getProducts.getProductname());
                                    textView.setTextSize(20);
                                    parentLayout.addView(textView);
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
