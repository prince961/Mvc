package com.indiakathi.mvc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Button bLogin;
    EditText etUsername , etPassword;
    TextView tvRegister;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etphone);
        etPassword = (EditText) findViewById(R.id.etPasswordLogin);
        //bLogin = (Button) findViewById(R.id.bLogin);
        //tvRegister = (Button) findViewById((R.id.tvRegisterLink));

        userLocalStore = new UserLocalStore(this);
    }

    public void LoginC(View view){

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        User user = new User(username,password);

        authentiacte(user);

        userLocalStore.storeUserData(user);
        userLocalStore.SetUserLoggedIn(true);
    }



    public void RegisterC(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);

    }
    private void authentiacte(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }



    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.SetUserLoggedIn(true);
        Intent intent = new Intent(this, selectBranch.class);
        startActivity(intent);
    }


}
