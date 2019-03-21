package com.example.parmila.milkmanager.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.parmila.milkmanager.Nav_Activity.Bills;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.Nav_Activity.Order_Now;
import com.example.parmila.milkmanager.R;
import com.example.parmila.milkmanager.SellerRecyclerAdapter;
import com.example.parmila.milkmanager.Nav_Activity.Setting;
import com.example.parmila.milkmanager.Nav_Activity.View_Orders;
import com.example.parmila.milkmanager.SessionManager;
import com.example.parmila.milkmanager.modules.Seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Catalog extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private RecyclerView recyclerSellerView;
    private List<Seller> listSeller;
    private SellerRecyclerAdapter sellerRecyclerAdapter;
    private DatabaseHelper helper=new DatabaseHelper(this);

    SessionManager shm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        helper.insertMilk();
        initNavigationDrawer();
        mDrawerLayout = findViewById(R.id.draw);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        initViews();
        initObjects();

    }
    protected void onStart()
    {
        super.onStart();
        shm=new SessionManager(getApplicationContext());
    }


    private void initViews() {
        recyclerSellerView = findViewById(R.id.recyclerViewSeller);
    }

    private void initObjects() {

        listSeller = new ArrayList<>();
        sellerRecyclerAdapter = new SellerRecyclerAdapter(listSeller);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration divider=new DividerItemDecoration(this,((LinearLayoutManager) mLayoutManager).getOrientation());
        recyclerSellerView.setLayoutManager(mLayoutManager);
        recyclerSellerView.setItemAnimator(new DefaultItemAnimator());
        recyclerSellerView.setHasFixedSize(true);
        recyclerSellerView.addItemDecoration(divider);
        recyclerSellerView.setAdapter(sellerRecyclerAdapter);
        helper = new DatabaseHelper(this);
        getDataFromSQLite();

    }

    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listSeller.clear();
                listSeller.addAll(helper.getAllSeller());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                sellerRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    public void initNavigationDrawer()
    {
        final NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Intent intent;
                switch (id)
                {
                    case  R.id.view_orders:
                        intent = new Intent(Catalog.this, View_Orders.class);
                        startActivity(intent);
                        break;

                    case R.id.bills:
                        intent = new Intent(Catalog.this, Bills.class);
                        startActivity(intent);
                        break;

                    case R.id.settings:
                        intent = new Intent(Catalog.this, Setting.class);
                        startActivity(intent);
                        break;

                    case R.id.Log_out:
                        onStart();
                        if(!shm.login()) {
                            intent = new Intent(Catalog.this, login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            break;
                        }
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_bar,menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView)searchItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                 sellerRecyclerAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}

