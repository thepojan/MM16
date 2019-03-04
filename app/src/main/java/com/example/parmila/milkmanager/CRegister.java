package com.example.parmila.milkmanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CRegister extends AppCompatActivity {

    DatabaseHelper dbHelper=new DatabaseHelper(this);

    private EditText reg_c_lname, reg_c_fname, reg_c_phone, reg_c_addr, reg_c_pin, reg_c_email, reg_c_pwd, reg_c_confirm_pwd;
    private Button reg_c_btn;

    String fname,lname,phone,address,pin,email,pass,cpass;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_register);
        reg_c_fname=findViewById(R.id.reg_c_fname);
        reg_c_lname=findViewById(R.id.reg_c_lname);
        reg_c_phone=findViewById(R.id.reg_c_phone);
        reg_c_addr=findViewById(R.id.reg_c_addr);
        reg_c_pin=findViewById(R.id.reg_c_pin);
        reg_c_email=findViewById(R.id.reg_c_email);
        reg_c_pwd=findViewById(R.id.reg_c_pwd);
        reg_c_confirm_pwd=findViewById(R.id.reg_c_confirm_pwd);
        reg_c_btn=findViewById(R.id.reg_c_confirm_button);



        reg_c_btn.setOnClickListener(new View.OnClickListener() {
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
           if (dbHelper.checkCust(reg_c_email.getText().toString().trim())) {registerSuccessfully();}
           else
           {
               Toast.makeText(this,"email already exists",Toast.LENGTH_SHORT).show();
           }

       }
       else Toast.makeText(this,"Registration failed!",Toast.LENGTH_SHORT).show();
   }

   public void initialize()
   {
        fname=reg_c_fname.getText().toString().trim();
       lname=reg_c_lname.getText().toString().trim();
       phone=reg_c_phone.getText().toString().trim();
       address=reg_c_addr.getText().toString().trim();
       pin=reg_c_pin.getText().toString().trim();
       email=reg_c_email.getText().toString().trim();
       pass=reg_c_pwd.getText().toString().trim();
       cpass=reg_c_confirm_pwd.getText().toString().trim();
   }

   public void registerSuccessfully() {
           Customer c = new Customer();
           c.setC_fname(fname);
           c.setC_lname(lname);
           c.setC_phone(phone);
           c.setC_address(address);
           c.setC_pin(pin);
           c.setC_email(email);
           c.setC_pass(pass);
           dbHelper.insertCustomer(c);
           Toast.makeText(this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
   }

   public boolean isValid()
   {
       boolean valid=true;
       final String pattern_c_name="[a-zA-Z]";
       //final String pattern_c_num="[0-9]";
       final String c_emailPattern="[a-zA-Z0-9.-_]+@[a-z]+\\.+[a-z]+";
       if((fname.isEmpty())||fname.equals(pattern_c_name)||fname.length()>15)
       {
           reg_c_fname.setError("invalid first name");
           valid=false;
       }
       if(lname.isEmpty()||lname.equals(pattern_c_name)||lname.length()>20)
       {
           reg_c_lname.setError("invalid last name");
           valid=false;
       }
       if((phone.isEmpty())||!(phone.length()==10))
       {
           reg_c_phone.setError("invalid phone");
           valid=false;
       }
       if(address.isEmpty())
       {
           reg_c_addr.setError("please enter address");
           valid=false;
       }
       if (pin.isEmpty()||!(pin.length()==6))
       {
           reg_c_pin.setError("invalid pin code");
           valid=false;
       }
       if(email.isEmpty()||email.equals(c_emailPattern))
       {
           reg_c_email.setError("invalid email");
           valid=false;
       }
       if(pass.isEmpty()||pass.length()<5)
       {
           reg_c_pwd.setError("password must contain atleast 5 characters/symbols");
           valid=false;
       }
       if(cpass.isEmpty()||!(cpass.equals(pass)))
       {
           reg_c_confirm_pwd.setError("password do not match");
           valid=false;
       }
       return valid;
   }

}