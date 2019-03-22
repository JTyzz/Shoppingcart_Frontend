package com.earthdefensesystem.shoppingcart_frontend.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    private long productid;
    private String productname, description;
    private double price;

    public Product(){

    }

    public Product(JSONObject json) {
        try {
            this.productid = json.getLong("productid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.productname = json.getString("productname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.description = json.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.price = json.getDouble("price");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
