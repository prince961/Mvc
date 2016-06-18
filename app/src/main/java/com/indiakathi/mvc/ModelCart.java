package com.indiakathi.mvc;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mohit on 04-04-2016.
 */
public class ModelCart{

    private ArrayList<HashMap<String,Object>> cartProducts = new ArrayList<HashMap<String, Object>>();


    public HashMap<String, Object> getProducts(int pPosition) {

        return cartProducts.get(pPosition);
    }

    public void setProducts(HashMap<String,Object> Products) {

        cartProducts.add(Products);

    }

    public int getCartSize() {

        return cartProducts.size();

    }

    public boolean checkProductInCart(ModelProducts aProduct) {

        return cartProducts.contains(aProduct);

    }

}