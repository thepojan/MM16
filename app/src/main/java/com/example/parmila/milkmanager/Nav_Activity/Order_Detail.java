package com.example.parmila.milkmanager.Nav_Activity;

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
    View_Order od=new View_Order();

    DatabaseHelper helper=new DatabaseHelper(this);


    Catalog c= c=new Catalog();
    String cust_email=c.email;

    TextView date,type,qtty,s_date,e_date,t_cost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        date=findViewById(R.id.o_date);
        type=findViewById(R.id.o_type);
        qtty=findViewById(R.id.o_qtty);
        s_date=findViewById(R.id.s_date);
        e_date=findViewById(R.id.e_date);
        t_cost=findViewById(R.id.t_cost);




    }
}
