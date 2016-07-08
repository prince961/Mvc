package com.indiakathi.mvc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mohit on 13-03-2016.
 */
public class JsonParserTry {

    public ArrayList<ModelProducts> parse(JSONObject jObject) {

        JSONArray jProducts = null;
        try {
            jProducts = jObject.getJSONArray("products");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getProducts(jProducts);
    }

    private ArrayList<ModelProducts> getProducts(JSONArray jProducts) {
        int productCount = jProducts.length();
            ArrayList<ModelProducts> productList = new ArrayList<ModelProducts>();
        ModelProducts product = null;

        for (int i = 0; i < productCount; i++) {
            try {
                product = getProduct((JSONObject) jProducts.get(i));
                productList.add(product);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    private ModelProducts getProduct(JSONObject jProduct) {

        //getting the image array cuz its inside another json object unlike the other details
        JSONArray imgArray = null ;
        try {
            imgArray = jProduct.getJSONArray("images");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject imgObject = null;
        try {
            imgObject = imgArray.getJSONObject(0);
        }catch (JSONException e) {
            e.printStackTrace();
        }

        String title = "";
        Integer price = null;
        String description = "";
        String imageLink = "";

        try {
            title = jProduct.getString("title");
            price = jProduct.getInt("sale_price");
            description = jProduct.getString("description");
            if (imgObject != null) {
                imageLink = imgObject.getString("src");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return new ModelProducts(title,description,price,imageLink,0);
    }
}
