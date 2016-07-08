package com.indiakathi.mvc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivityTry extends AppCompatActivity {

    ListView mListView;
    List<ModelProducts> productsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        mListView = (ListView) findViewById(R.id.productListViewA);
        final Button secondBtn = (Button) findViewById(R.id.second);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);

        secondBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fab.setVisibility(View.VISIBLE);
            }
        });
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        for (int i = 1; i <= 4; i++) {
            int price = 10 + i;
            // Create product model class object
            productsList.add(new ModelProducts(i+"Product",i+"des",i+200,"www",0));
        }
        mListView.setAdapter(new AdapterTry(this,R.layout.item_list,productsList));
    }
}

