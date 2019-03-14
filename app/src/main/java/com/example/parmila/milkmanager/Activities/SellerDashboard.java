package com.example.parmila.milkmanager.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SellerDashboard extends AppCompatActivity {
    public String s_id= getIntent().getStringExtra("S-ID");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
