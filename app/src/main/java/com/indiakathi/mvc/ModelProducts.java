package com.indiakathi.mvc;

import java.util.HashMap;

public class ModelProducts {

    private String productName;
    private String productDesc;
    private int productPrice;
    private String imageLink;

    public ModelProducts(String productName, String productDesc, int productPrice, String imageLink)
    {
        this.productName  = productName;

        this.productDesc  = productDesc;
        this.productPrice = productPrice;
        this.imageLink    = imageLink;
    }



    public String getProductName() {

        return productName;
    }

    public String getProductDesc() {

        return productDesc;
    }

    public int getProductPrice() {

        return productPrice;
    }


    public String getImageLink() {

        return imageLink;
    }


}
