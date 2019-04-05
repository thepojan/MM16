package com.example.parmila.milkmanager.Nav_Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;

@SuppressLint("Registered")
public class Bill_Details extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);
    public static String bill_id;
    TextView b_id, seller_name, cust_name, m_type, qtty, s_date, e_date, days,t_cost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);

        Intent i=getIntent();

        bill_id=i.getStringExtra("Bill_ID");

        Log.d("Bill-details:"," "+bill_id);

        b_id=findViewById(R.id.bill_no);
        seller_name=findViewById(R.id.s_name);
        cust_name=findViewById(R.id.c_name);
        m_type=findViewById(R.id.type);
        qtty=findViewById(R.id.qtty);
        s_date=findViewById(R.id.start);
        e_date=findViewById(R.id.end);
        days=findViewById(R.id.days);
        t_cost=findViewById(R.id.t_cost);


        Cursor c=helper.getBill(bill_id);

        if(c!=null && c.moveToNext())
        {
            b_id.setText(c.getString(0));
            seller_name.setText(c.getString(1));
            cust_name.setText(c.getString(2));
            m_type.setText(c.getString(3));
            qtty.setText(c.getString(4));
            s_date.setText(c.getString(5));
            e_date.setText(c.getString(6));
            days.setText(c.getString(7));
            t_cost.setText("\u20B9 "+c.getString(8));
        }
    }
}
