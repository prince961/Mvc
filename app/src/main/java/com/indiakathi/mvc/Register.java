package com.indiakathi.mvc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText etname, etusername, etpassword, etage;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etname = (EditText) findViewById(R.id.etName);
        etusername = (EditText) findViewById(R.id.etphone);
        etage = (EditText) findViewById(R.id.etAge);
        etpassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);


    }

    public void RegisterCr(View view){

        String name = etname.getText().toString();
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        int age = Integer.parseInt(etage.getText().toString());

        User user = new User(name ,age , username , password );
        registerUser(user);
    }

    public void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
            }
        });
    }

}
