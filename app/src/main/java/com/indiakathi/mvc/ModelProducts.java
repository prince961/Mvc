package com.indiakathi.mvc;

public class ModelProducts {

    private String productName;
    private String productDesc;
    private int productPrice;
    private String imageLink;
    private int productQuantity;


    public ModelProducts(String productName, String productDesc, int productPrice, String imageLink,int quantity)
    {
        this.productName  = productName;
        this.productQuantity = quantity;
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

    public int getProductQuantity() {

        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
