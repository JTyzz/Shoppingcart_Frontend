package com.earthdefensesystem.shoppingcart_frontend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.earthdefensesystem.shoppingcart_frontend.model.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityDogs";

    EditText productName, productDescription, productId, productPrice;
    Button putButton, postButton, deleteButton, productListButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productName = findViewById(R.id.product_name_text);
        productDescription = findViewById(R.id.description_text);
        productId = findViewById(R.id.productid_text);
        productPrice = findViewById(R.id.price_text);
        putButton = findViewById(R.id.put_button);
        postButton = findViewById(R.id.post_button);
        deleteButton = findViewById(R.id.button3);
        productListButton = findViewById(R.id.product_list_button);
        context = this;


        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject productdata = new JSONObject();
                        try {
                            productdata.put("productname", productName.getText().toString());
                            productdata.put("productid", productId.getText().toString());
                            productdata.put("description", productDescription.getText().toString());
                            productdata.put("price", productPrice.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String tokenRequest = null;
                        try {
                            tokenRequest = NetworkAdapter.httpRequest(
                                    "http://10.0.2.2:1932/product",
                                    "POST", productdata, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.i(TAG, tokenRequest);
                    }
                }).start();
            }
        });
        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject productdata = new JSONObject();
                        try {
                            productdata.put("productname", productName.getText().toString());
                            productdata.put("productid", productId.getText().toString());
                            productdata.put("description", productDescription.getText().toString());
                            productdata.put("price", productPrice.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String tokenRequest = null;
                        try {
                            tokenRequest = NetworkAdapter.httpRequest(
                                    "http://10.0.2.2:1932/product/" + productId.getText().toString(),
                                    "PUT", productdata, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.i(TAG, tokenRequest);
                    }
                }).start();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String tokenRequest = null;
                        try {
                            tokenRequest = NetworkAdapter.httpRequest(
                                    "http://10.0.2.2:1932/product/" + productId.getText().toString(),
                                    "DELETE", null, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.i(TAG, tokenRequest);
                    }
                }).start();
            }
        });

        productListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
            }
        });
    }

}
