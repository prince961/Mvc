package com.indiakathi.mvc;

/**
 * Created by mohit on 13-06-2016.
 */
public class User {

    String name, username, password;
    int age;

    public User(String name,int age, String username , String password  ){
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User(String username, String password) {
        this("", -1, username, password);
    }

}
