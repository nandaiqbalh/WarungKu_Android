package com.nandaiqbalh.warungku.rest;

import com.nandaiqbalh.warungku.model.Product;

import java.util.ArrayList;

public class ProductResponse {

    private int success = 0;
    private String message;
    private ArrayList<Product> product = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }
}
