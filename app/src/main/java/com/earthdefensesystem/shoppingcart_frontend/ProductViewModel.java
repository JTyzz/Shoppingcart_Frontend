package com.earthdefensesystem.shoppingcart_frontend;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.earthdefensesystem.shoppingcart_frontend.model.Product;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Product>> overviewList;

    public LiveData<ArrayList<Product>> getProductViewList() {
        if(overviewList == null) {
            loadList();
        }
        return overviewList;
    }
    private void loadList() {
        overviewList = ProductRepo.getProductList();
    }
}