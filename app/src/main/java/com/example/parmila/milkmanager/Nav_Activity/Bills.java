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

import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.BillRecyclerAdapter;
import com.example.parmila.milkmanager.modules.Bill;

import java.util.ArrayList;
import java.util.List;

public class Bills extends AppCompatActivity implements BillRecyclerAdapter.OnBillListener{
    private RecyclerView recyclerBillView;
    private List<Bill> listBill;
    private BillRecyclerAdapter billRecyclerAdapter;
    private DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        initViews();
        initObjects();

    }

    private void initViews() {
        recyclerBillView = findViewById(R.id.recyclerViewBill);
    }

    private void initObjects() {

        listBill = new ArrayList<>();
        billRecyclerAdapter = new BillRecyclerAdapter(listBill,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration divider=new DividerItemDecoration(this,((LinearLayoutManager) mLayoutManager).getOrientation());
        recyclerBillView.setLayoutManager(mLayoutManager);
        recyclerBillView.setItemAnimator(new DefaultItemAnimator());
        recyclerBillView.setHasFixedSize(true);
        recyclerBillView.addItemDecoration(divider);
        recyclerBillView.setAdapter(billRecyclerAdapter);
        helper = new DatabaseHelper(this);
        getDataFromSQLite();

    }
    @Override
    public void onBillClick(int position)
    {
        Intent i=new Intent(this,Bill_Details.class);
        startActivity(i);
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listBill.clear();
                listBill.addAll(helper.getAllBills());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                billRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();

    }
}