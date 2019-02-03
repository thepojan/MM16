package com.example.parmila.milkmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
     private EditText userName;
     private EditText password;
     private Button login, register;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the UI of login page

        setContentView(R.layout.activity_login);
        userName=findViewById(R.id.et_username);
        password= findViewById(R.id.et_pwd);
        login=findViewById(R.id.btn_login);
        register=findViewById(R.id.btn_register);

        // Button click to login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginValid()) {
                    Intent catalogIntent = new Intent(login.this, Catalog.class);
                    login.this.startActivity(catalogIntent);
                }
            }
        });


    }


        // function to validate the login credentials
        boolean loginValid ()
        {
            boolean valid=false;
            String name=userName.getText().toString();
            String pwd=password.getText().toString();
            if(name.equals("jop") && pwd.equals("1998"))
            {
                valid=true;
            }
            else if((name.isEmpty() || pwd.isEmpty()))
            {
                Toast.makeText(this,"please enter all the credentials",Toast.LENGTH_SHORT).show();
                valid=false;
            }
            else if(!name.equals("jop") || !pwd.equals("1998"))
            {
                Toast.makeText(this,"please enter valid credentials",Toast.LENGTH_SHORT).show();
                valid=false;
            }
            return valid;
        }

}
