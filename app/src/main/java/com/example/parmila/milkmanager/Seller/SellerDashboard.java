package com.example.parmila.milkmanager.Seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.Activities.login;
import com.example.parmila.milkmanager.MySession.PreferenceUtils;
import com.example.parmila.milkmanager.Nav_Activity.Bills;
import com.example.parmila.milkmanager.Nav_Activity.View_Orders;
import com.example.parmila.milkmanager.OrderRecyclerAdapter;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.modules.Bill;
import com.example.parmila.milkmanager.modules.View_Order;

public class SellerDashboard extends AppCompatActivity {


    DatabaseHelper helper = new DatabaseHelper(this);

    LinearLayout bill, order, setting;
    TextView count, name, email, logout;


    public static String s_email;

    Catalog c=new Catalog();
    String c_email=c.email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);

        Intent i = getIntent();
        s_email = i.getStringExtra("S_Email");


        Log.d("Seller Dashboard:", "Extra intent string" + s_email);
        initView();


        String sname=helper.getSellName(s_email);
        name.setText(helper.getSellName(s_email).toUpperCase());
        email.setText(s_email);
        count.setText(Integer.toString(helper.custCount(sname)));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.saveEmail(null, SellerDashboard.this);
                PreferenceUtils.savePassword(null, SellerDashboard.this);
                Intent i = new Intent(SellerDashboard.this, login.class);
                startActivity(i);
                finish();
            }
        });

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerDashboard.this, Bills.class);
                i.putExtra("FROM", "SellerDashBoard");
                startActivity(i);
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SellerDashboard.this, View_Orders.class);
                i.putExtra("FROM", "SellerDashBoard");
                startActivity(i);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellerDashboard.this, Seller_Setting.class);
                startActivity(i);
            }
        });

    }


    public void initView() {
        name = findViewById(R.id.s_name);
        email = findViewById(R.id.s_email);
        bill = findViewById(R.id.bill);
        order = findViewById(R.id.order);
        setting = findViewById(R.id.setting);
        count = findViewById(R.id.cust_count);
        logout = findViewById(R.id.logout);
    }

}