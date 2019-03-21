package com.example.parmila.milkmanager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parmila.milkmanager.modules.View_Order;

import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.OrderViewHolder>

{
    private List<View_Order> listOrder;
    private OnOrderListener monOrderListener;

    public OrderRecyclerAdapter(List<View_Order> listOrder, OnOrderListener onOrderListener) {
        this.listOrder = listOrder;
        this.monOrderListener = onOrderListener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_recycler_view, parent, false);
        return new OrderViewHolder(itemView, monOrderListener);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.date.setText(listOrder.get(position).getV_start());
        holder.f_cost.setText("\u20B9 " + String.valueOf(listOrder.get(position).getV_fcost()));
    }

    @Override
    public int getItemCount() {
        Log.v(OrderRecyclerAdapter.class.getSimpleName(), "" + listOrder.size());
        return listOrder.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView date, f_cost;

        public OrderViewHolder(final View view, OnOrderListener ool) {
            super(view);
            date = view.findViewById(R.id.date);
            f_cost = view.findViewById(R.id.f_cost);
            ool = monOrderListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            monOrderListener.onOrderClick(getAdapterPosition());
        }
    }

    public interface OnOrderListener {
        public void onOrderClick(int position);
    }

}


