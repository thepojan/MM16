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

    static String o_date;


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
        holder.type.setText(listOrder.get(position).getV_type());
        holder.quantity.setText(String.valueOf(listOrder.get(position).getV_qtty()));
        holder.f_cost.setText("\u20B9 " + String.valueOf(listOrder.get(position).getV_fcost()));
    }

    @Override
    public int getItemCount() {
        Log.v(OrderRecyclerAdapter.class.getSimpleName(), "" + listOrder.size());
        return listOrder.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView type,quantity,f_cost;

        public OrderViewHolder(final View view, OnOrderListener ool) {
            super(view);
            type = view.findViewById(R.id.mvtype);
            quantity=view.findViewById(R.id.vquantity);
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
         void onOrderClick(int position);
    }

}


