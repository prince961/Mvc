package com.indiakathi.mvc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class selectBranch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_branch);
    }


    public void sector14(View view) {
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }

    public void sector56(View view) {
        Intent intent = new Intent(this, AdapterActivityTry.class);
        startActivity(intent);
    }
}
