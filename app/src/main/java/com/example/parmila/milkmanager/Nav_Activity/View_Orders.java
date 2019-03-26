package com.example.parmila.milkmanager.Nav_Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.OrderRecyclerAdapter;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.modules.View_Order;

import java.util.ArrayList;
import java.util.List;

public class View_Orders extends AppCompatActivity implements OrderRecyclerAdapter.OnOrderListener{
    private RecyclerView recyclerOrderView;
    private List<View_Order> listOrder;
    private OrderRecyclerAdapter orderRecyclerAdapter;
    private DatabaseHelper helper = new DatabaseHelper(this);


    Catalog c= new Catalog();
    String cEmail=c.email;
    Order_Now o_n=new Order_Now();
    String sEmail=o_n.s_email;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        initViews();
        initObjects();
    }

    private void initViews() {
        recyclerOrderView = findViewById(R.id.recyclerViewOrder);
    }

    private void initObjects() {

        listOrder = new ArrayList<>();
        orderRecyclerAdapter = new OrderRecyclerAdapter(listOrder,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration divider=new DividerItemDecoration(this,((LinearLayoutManager) mLayoutManager).getOrientation());
        recyclerOrderView.setLayoutManager(mLayoutManager);
        recyclerOrderView.setItemAnimator(new DefaultItemAnimator());
        recyclerOrderView.setHasFixedSize(true);
        recyclerOrderView.addItemDecoration(divider);
        recyclerOrderView.setAdapter(orderRecyclerAdapter);
        helper = new DatabaseHelper(this);
        getDataFromSQLite();

    }


    @Override
    public void onOrderClick(int position)
    {
        Intent i=new Intent(this,Order_Detail.class);
        startActivity(i);
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.

        final String cname=helper.getCustName(cEmail);
        final String sname=helper.getSellName(sEmail);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listOrder.clear();
                listOrder.addAll(helper.getAllOrders(cname,sname));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                orderRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
