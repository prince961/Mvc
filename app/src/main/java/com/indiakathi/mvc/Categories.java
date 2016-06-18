package com.indiakathi.mvc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void ChineseStarters(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }

    public void Rolls(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }

    public void tandooriItem(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }

    public void vegStarters(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }

    public void FriedRice(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }

    public void Noodles(View view) {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
    }
}
