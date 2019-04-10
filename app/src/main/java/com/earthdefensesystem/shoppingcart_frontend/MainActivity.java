package com.earthdefensesystem.shoppingcart_frontend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.earthdefensesystem.shoppingcart_frontend.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.earthdefensesystem.shoppingcart_frontend.ShoppingDAO.PRODUCT_URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityDogs";

    LinearLayout parentLayout;
    Context context;
    Button listButton, productEntryButton;
    EditText usernameText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listButton = findViewById(R.id.loadlist);
        productEntryButton = findViewById(R.id.product_entry_button);
        usernameText = findViewById(R.id.username_text);
        passwordText= findViewById(R.id.password_text);

        parentLayout = findViewById(R.id.parent_layout);
        context = this;

        final ArrayList<Product> data = new ArrayList<>();

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String auth = Base64.encodeToString("lambda-client:lambda-secret".getBytes(), Base64.DEFAULT);

                        Map<String, String> headerProperties = new HashMap<>();
                        headerProperties.put("Authorization", "Basic " + auth);

                        String tokenUrl= "http://10.0.2.2:1932/oauth/token?grant_type=password&username="
                                +usernameText.getText().toString()+"&password="
                                +passwordText.getText().toString()+"&scope=";

                        String tokenRequest = null;
                        try {
                            tokenRequest = NetworkAdapter.httpRequest(
                                    tokenUrl, "POST", null, headerProperties);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.i(TAG, tokenRequest);
                        try {
                            String token = new JSONObject(tokenRequest).getString("access_token");

                            headerProperties.clear();
                            headerProperties.put("Authorization", "Bearer " + token);
                                try {
                                    String result = null;
                                    try {
                                        result = NetworkAdapter.httpRequest(PRODUCT_URL, "GET", null, headerProperties);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    JSONArray dataJsonArray = new JSONArray(result);

                                    for (int i = 0; i < dataJsonArray.length(); ++i) {
                                        Product product = new Product(dataJsonArray.getJSONObject(i));
                                        data.add(product);
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            for(int i = 0; i < data.size(); i++) {
                                                TextView textView = new TextView(context);
                                                final Product getProducts = data.get(i);
                                                textView.setText(getProducts.getDescription()+ " " + getProducts.getProductname());
                                                textView.setTextSize(20);
                                                parentLayout.addView(textView);
                                            }
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        productEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductActivity.class);
                startActivity(i);
            }
        });
    }

}
