package com.indiakathi.mvc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);

        TextView showCartContent    = (TextView) findViewById(R.id.showCart);

        //Get Global Controller Class object (see application tag in AndroidManifest.xml)
        final Controller aController = (Controller) getApplicationContext();

        int cartSize = aController.getCart().getCartSize();

        String showString = "";

/******** Show Cart Products on screen - Start ********/

        for(int i=0;i<cartSize;i++)
        {
            //Get product details
            String pName 	= (String) aController.getCart().getProducts(i).get("title");
            int pPrice   	= (int) aController.getCart().getProducts(i).get("price");
            String pDisc   	= (String) aController.getCart().getProducts(i).get("details");

            showString += "\n\nProduct Name : "+pName+"\n"+
                    "Price : "+pPrice+"\n"+
                    "Discription : "+pDisc+""+
                    "\n -----------------------------------";
        }


        showCartContent.setText(showString);

/******** Show Cart Products on screen - End ********/

    }

}
