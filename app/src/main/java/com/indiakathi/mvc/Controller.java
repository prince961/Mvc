package com.indiakathi.mvc;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohit on 04-04-2016.
 */
public class Controller extends Application {

    private ArrayList<ModelProducts> myProducts = new ArrayList<ModelProducts>();
    private  ModelCart myCart = new ModelCart();


    public ModelProducts getProducts(int pPosition) {

        return myProducts.get(pPosition);
    }

    public void addProductList(List<ModelProducts> productList){
        myProducts.addAll(productList);
    }

    public void setProducts(ModelProducts Products) {

        myProducts.add(Products);

    }

    public ModelCart getCart() {

        return myCart;

    }

    public int getProductsArraylistSize() {

        return myProducts.size();
    }

}
