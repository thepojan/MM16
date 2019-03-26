package com.example.parmila.milkmanager;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.parmila.milkmanager.Activities.Catalog;
import com.example.parmila.milkmanager.Nav_Activity.Order_Now;
import com.example.parmila.milkmanager.SQLite.DatabaseHelper;
import com.example.parmila.milkmanager.modules.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerRecyclerAdapter extends RecyclerView.Adapter<SellerRecyclerAdapter.SellerViewHolder> implements Filterable

{
    //Catalog ca=new Catalog();
    //String s_email=" ";
   // DatabaseHelper helper;
    private List<Seller> listSeller;
    private List<Seller> seller_list;
    public SellerRecyclerAdapter(List<Seller> listSeller) {
        this.listSeller = listSeller;
        seller_list=new ArrayList<>(listSeller);
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
        holder.email.setText(listSeller.get(position).getS_email());

    }
    @Override
    public int getItemCount() {
        Log.v(SellerRecyclerAdapter.class.getSimpleName(),""+listSeller.size());
        return listSeller.size();
    }

    @Override
    public Filter getFilter() {
        return filter_list;
    }
    private Filter filter_list=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Seller> filteredList=new ArrayList<>();

            if(constraint==null|| constraint.length()==0)
            {
                filteredList.addAll(seller_list);
            }
            else
            {
                String filteredPattern=constraint.toString().toLowerCase().trim();

                for(Seller item: seller_list)
                {
                    if((item.getS_fname()+" "+item.getS_lname()).toLowerCase().contains(filteredPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results =new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            //listSeller.clear();
            listSeller.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };


    public class SellerViewHolder extends RecyclerView.ViewHolder {
        public TextView name,phone, area, email ;
        public Button order;

        public SellerViewHolder(final View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            area =  view.findViewById(R.id.area);
            order=view.findViewById(R.id.order);
            email=view.findViewById(R.id.email);

            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final String s_email=listSeller.get(position).getS_email();
                    Intent orderIntent=new Intent(view.getContext(),Order_Now.class);
                    orderIntent.putExtra("S_Email",s_email);
                    view.getContext().startActivity(orderIntent);
                }
            });
        }
    }

}
