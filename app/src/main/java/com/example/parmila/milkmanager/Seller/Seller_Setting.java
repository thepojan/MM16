package com.example.parmila.milkmanager.Seller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.Delete;
import com.example.parmila.milkmanager.Nav_Activity.Order_Now;
import com.example.parmila.milkmanager.Nav_Activity.Setting;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;

public class Seller_Setting extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);


    SellerDashboard sd=new SellerDashboard();
    public String sEmail=sd.s_email;


    TextView name,email,phone,zip,delete;
    Button back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_setting);
        initViews();
        setViews();

        Log.d("S_Setting:",""+sEmail);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(Seller_Setting.this);
                a_builder.setMessage("Are you sure you want to delete your account?").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent i=new Intent(Seller_Setting.this,Delete.class);
                        Seller_Setting.this.startActivity(i);

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
        name.setText(helper.getSellName(sEmail));
        email.setText(sEmail);
        phone.setText(helper.getSellPhone(sEmail));
        zip.setText(helper.getSellZip(sEmail));
    }

    private void initViews() {
        name=findViewById(R.id.sname);
        email=findViewById(R.id.semail);
        phone=findViewById(R.id.sphone);
        zip=findViewById(R.id.szip);
        delete=findViewById(R.id.sdelete);
    }
}
