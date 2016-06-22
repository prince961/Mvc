package com.indiakathi.mvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivityTry extends AppCompatActivity {

    ListView mListView;
    List<ModelProducts> productsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_activity_try);
        mListView = (ListView) findViewById(R.id.productListViewA);


        for (int i = 1; i <= 4; i++) {
            int price = 10 + i;
            // Create product model class object
            productsList.add(new ModelProducts(i+"Product",i+"des",i,"www"));
        }
        mListView.setAdapter(new AdapterTry(this,R.layout.item_list,productsList));
    }
}

