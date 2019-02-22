package com.example.parmila.milkmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class CRegister extends AppCompatActivity {

    private EditText reg_name, reg_phone, reg_addr, reg_pin, reg_email, reg_pwd, reg_confirm_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_register);

    }
}