package com.example.parmila.milkmanager.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parmila.milkmanager.MySession.PreferenceUtils;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.Seller.SellerDashboard;

public class login extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    private EditText email;
    private EditText password;
    private Button login, register;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the UI of login page

        setContentView(R.layout.activity_login);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pwd);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        if (PreferenceUtils.getEmail(this) != null) {
            String email = PreferenceUtils.getEmail(this);
            Log.d("tag:Session", email);

            if (!helper.checkCust(email)) {
                Intent catalogIntent = new Intent(login.this, Catalog.class);
                catalogIntent.putExtra("C_Email", email);
                login.this.startActivity(catalogIntent);
            }

            else if(!helper.checkSeller(email))
            {
                Intent SellerDashboardIntent = new Intent(login.this, SellerDashboard.class);
                SellerDashboardIntent.putExtra("S_Email", email);
                login.this.startActivity(SellerDashboardIntent);
            }
        }

        // login button click event
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginValid();
            }
        });

        // register button click event
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(login.this, RegisterType.class);
                login.this.startActivity(registerIntent);
            }
        });
    }


    // function to validate the login credentials
    public void loginValid() {
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(this, "please enter all details", Toast.LENGTH_SHORT).show();
        } else if (helper.checkCust(email.getText().toString().trim(), password.getText().toString().trim())) {

            String email1, password1;
            email1 = email.getText().toString().trim();
            password1 = password.getText().toString().trim();

            PreferenceUtils.saveEmail(email1, this);
            PreferenceUtils.savePassword(password1, this);

            Intent catalogIntent = new Intent(login.this, Catalog.class);
            catalogIntent.putExtra("C_Email", email1);
            login.this.startActivity(catalogIntent);

        } else if (helper.checkSeller(email.getText().toString().trim(), password.getText().toString().trim())) {
            String email1, password1;
            email1 = email.getText().toString().trim();
            password1 = password.getText().toString().trim();

            PreferenceUtils.saveEmail(email1, this);
            PreferenceUtils.savePassword(password1, this);

            Intent SellerDashboardIntent = new Intent(login.this, SellerDashboard.class);
            SellerDashboardIntent.putExtra("S_Email", email1);
            login.this.startActivity(SellerDashboardIntent);
        } else {
            Toast.makeText(this, "invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
