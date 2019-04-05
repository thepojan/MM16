package com.example.parmila.milkmanager.Nav_Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.widget.TextView;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.modules.Order;
import com.example.parmila.milkmanager.modules.View_Order;


public class Order_Detail extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    public static String O_Date;
    public static int Cost;


    TextView date, c_name, s_name, c_addr, type, qtty, s_date, e_date, t_cost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent i = getIntent();
        O_Date = i.getStringExtra("Order_date");
        Cost = i.getIntExtra("F_Cost", 0);
        c_addr = findViewById(R.id.cust_addr);
        c_name = findViewById(R.id.o_by);
        s_name = findViewById(R.id.o_to);
        date = findViewById(R.id.o_date);
        type = findViewById(R.id.o_type);
        qtty = findViewById(R.id.o_qtty);
        s_date = findViewById(R.id.s_date);
        e_date = findViewById(R.id.e_date);
        t_cost = findViewById(R.id.t_cost);


        Cursor c = helper.getViewOrder(O_Date, Cost);


        if (c!=null && c.moveToNext())
        {
            type.setText(c.getString(0));
            s_name.setText(c.getString(1));
            c_name.setText(c.getString(2));
            c_addr.setText(c.getString(3));
            qtty.setText(c.getString(4));
            s_date.setText(c.getString(5));
            e_date.setText(c.getString(6));
            date.setText(c.getString(7));
            t_cost.setText("\u20B9 "+c.getString(8));
        }

    }
}
