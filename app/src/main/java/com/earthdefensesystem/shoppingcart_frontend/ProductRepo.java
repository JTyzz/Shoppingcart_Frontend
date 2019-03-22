package com.earthdefensesystem.shoppingcart_frontend;

import android.arch.lifecycle.MutableLiveData;

import com.earthdefensesystem.shoppingcart_frontend.model.Product;

import java.util.ArrayList;

public class ProductRepo {
    public static MutableLiveData<ArrayList<Product>> getProductList() {
        final MutableLiveData<ArrayList<Product>> liveDataList = new MutableLiveData<>();
        ArrayList<Product> overviews = ShoppingDAO.getProductList();
        liveDataList.setValue(overviews);
        return liveDataList;
    }
}
