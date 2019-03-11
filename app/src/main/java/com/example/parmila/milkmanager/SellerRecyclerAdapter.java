package com.example.parmila.milkmanager;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.modules.Seller;

import java.util.List;

public class SellerRecyclerAdapter extends RecyclerView.Adapter<SellerRecyclerAdapter.SellerViewHolder>

{
    private List<Seller> listSeller;
    public SellerRecyclerAdapter(List<Seller> listSeller) {
        this.listSeller = listSeller;
    }
    @Override
    public SellerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycle_view, parent, false);
        return new SellerViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(SellerViewHolder holder, int position) {
        holder.name.setText(listSeller.get(position).getS_fname()+" "+listSeller.get(position).getS_lname());
        holder.phone.setText(listSeller.get(position).getS_phone());
        holder.area.setText(listSeller.get(position).getS_pin());
    }
    @Override
    public int getItemCount() {
        Log.v(SellerRecyclerAdapter.class.getSimpleName(),""+listSeller.size());
        return listSeller.size();
    }

    public class SellerViewHolder extends RecyclerView.ViewHolder {
        public TextView name,phone, area ;
        public Button order;

        public SellerViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            area =  view.findViewById(R.id.area);
            order=view.findViewById(R.id.order);
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(SellerRecyclerAdapter.this, Catalog.class);
                }
            });
        }

    }



}
