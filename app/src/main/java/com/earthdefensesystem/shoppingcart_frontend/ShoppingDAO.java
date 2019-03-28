package com.earthdefensesystem.shoppingcart_frontend;

import android.util.Log;

import com.earthdefensesystem.shoppingcart_frontend.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShoppingDAO {
    public static final String BASE_URL = "http://10.0.2.2:1932";
    public static final String PRODUCT_URL = BASE_URL + "/product";

    public interface ObjectCallback<T> {
        void returnObjects(T object);
    }

    public static ArrayList<Product> getProductList() {
        ArrayList<Product> data = new ArrayList<>();

        try {
            String result = NetworkAdapter.httpRequest(PRODUCT_URL, NetworkAdapter.GET, null, null);
//            JSONObject dataObject = new JSONObject(result);
            JSONArray dataJsonArray = new JSONArray(result);

            for (int i = 0; i < dataJsonArray.length(); ++i) {
                Product product = new Product(dataJsonArray.getJSONObject(i));
                data.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

//    public static Product addProduct() {
//        Product productdata = new Product(5, "Juice", "Cold", 4.35);
//
//        try {
//            String result = NetworkAdapter.httpRequest(PRODUCT_URL, NetworkAdapter.POST, productdata);
//            JSONObject dataObject = new JSONObject(result);
//            JSONArray dataJsonArray = new JSONArray(result);
//
//            dataObject.put("productname", "Termites");
//            dataObject.put("productid", "8");
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
//    public static Product addProduct(){
//
//    }



}