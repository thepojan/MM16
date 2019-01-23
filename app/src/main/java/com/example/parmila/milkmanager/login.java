package com.example.parmila.milkmanager;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class login extends AppCompatActivity {
     private EditText username;
     private EditText pwd;

   //public login() {
   // }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        pwd= findViewById(R.id.pwd);

    }
}
