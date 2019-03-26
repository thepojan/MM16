package com.example.parmila.milkmanager.Nav_Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.Delete;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;

public class Setting extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);

    Catalog c= c=new Catalog();
    String cEmail=c.email;
    Order_Now o_n=new Order_Now();
    String sEmail=o_n.s_email;


    TextView name,email,address,phone,zip,delete;
    Button back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initViews();
        setViews();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Setting.this, Catalog.class);
                Setting.this.startActivity(i);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(Setting.this);
                a_builder.setMessage("Are you sure you want to delete your account?").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent i=new Intent(Setting.this,Delete.class);
                        Setting.this.startActivity(i);

                    }
                }).setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert !!!");
                alert.show();
                }
        });

    }

    private void setViews() {
        name.setText(helper.getCustName(cEmail));
        email.setText(cEmail);
        address.setText(helper.getCustAddr(cEmail));
        phone.setText(helper.getCustPhone(cEmail));
        zip.setText(helper.getCustZip(cEmail));
    }

    private void initViews() {
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.phone);
        zip=findViewById(R.id.zip);
        delete=findViewById(R.id.delete);
        back=findViewById(R.id.back);
    }
}
