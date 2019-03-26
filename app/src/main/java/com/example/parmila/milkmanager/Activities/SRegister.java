package com.example.parmila.milkmanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.modules.Seller;

public class SRegister extends AppCompatActivity {


    DatabaseHelper dbHelper=new DatabaseHelper(this);

    private EditText reg_s_lname, reg_s_fname, reg_s_phone, reg_s_pin, reg_s_email, reg_s_pwd, reg_s_confirm_pwd;
    private Button reg_s_btn;

    String fname,lname,phone,pin,email,pass,cpass;
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);

        reg_s_fname=findViewById(R.id.reg_s_fname);
        reg_s_lname=findViewById(R.id.reg_s_lname);
        reg_s_phone=findViewById(R.id.reg_s_phone);
        reg_s_pin=findViewById(R.id.reg_s_pin);
        reg_s_email=findViewById(R.id.reg_s_email);
        reg_s_pwd=findViewById(R.id.reg_s_pwd);
        reg_s_confirm_pwd=findViewById(R.id.reg_s_confirm_pwd);
        reg_s_btn=findViewById(R.id.register_s_confirm_button);

        reg_s_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    public void register()
    {
        initialize();
        if(isValid())
        {
            if (dbHelper.checkSeller(reg_s_email.getText().toString().trim())){registerSuccessfully();}
            else
            {
                Toast.makeText(this,"email already exists",Toast.LENGTH_SHORT).show();
            }

        }
        else Toast.makeText(this,"Registration failed!",Toast.LENGTH_SHORT).show();
    }

    public void initialize()
    {
        fname=reg_s_fname.getText().toString().trim();
        lname=reg_s_lname.getText().toString().trim();
        phone=reg_s_phone.getText().toString().trim();
        pin=reg_s_pin.getText().toString().trim();
        email=reg_s_email.getText().toString().trim();
        pass=reg_s_pwd.getText().toString().trim();
        cpass=reg_s_confirm_pwd.getText().toString().trim();
    }

    public void registerSuccessfully() {
        Seller s = new Seller();
        s.setS_fname(fname);
        s.setS_lname(lname);
        s.setS_phone(phone);
        s.setS_pin(pin);
        s.setS_email(email);
        s.setS_pass(pass);
        dbHelper.insertSeller(s);
        Toast.makeText(this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,login.class);
        startActivity(i);
    }

    public boolean isValid()
    {
        boolean valid=true;
        final String pattern_c_name="[a-zA-Z]";
        //final String pattern_c_num="[0-9]";
        final String c_emailPattern="[a-zA-Z0-9.-_]+@[a-z]+\\.+[a-z]+";
        if((fname.isEmpty())||fname.equals(pattern_c_name)||fname.length()>15)
        {
            reg_s_fname.setError("invalid first name");
            valid=false;
        }
        if(lname.isEmpty()||lname.equals(pattern_c_name)||lname.length()>20)
        {
            reg_s_lname.setError("invalid last name");
            valid=false;
        }
        if((phone.isEmpty())||!(phone.length()==10))
        {
            reg_s_phone.setError("invalid phone");
            valid=false;
        }
        if (pin.isEmpty()||!(pin.length()==6))
        {
            reg_s_pin.setError("invalid pin code");
            valid=false;
        }
        if(email.isEmpty()||email.equals(c_emailPattern))
        {
            reg_s_email.setError("invalid email");
            valid=false;
        }
        if(pass.isEmpty()||pass.length()<5)
        {
            reg_s_pwd.setError("password must contain atleast 5 characters/symbols");
            valid=false;
        }
        if(cpass.isEmpty()||!(cpass.equals(pass)))
        {
            reg_s_confirm_pwd.setError("password do not match");
            valid=false;
        }
        return valid;
    }

}

