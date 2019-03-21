package com.example.parmila.milkmanager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.parmila.milkmanager.Nav_Activity.Bills;
import com.example.parmila.milkmanager.modules.Bill;

import java.util.List;

public class BillRecyclerAdapter extends RecyclerView.Adapter<BillRecyclerAdapter.BillViewHolder>

{
    private List<Bill> listBill;
    private  OnBillListener monBillListener;

    public BillRecyclerAdapter(List<Bill> listBill, Bills obl) {
        this.listBill = listBill;
        this.monBillListener=obl;
    }

    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bill_recycler_view, parent, false);
        return new BillViewHolder(itemView,monBillListener);
    }
    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        holder.start.setText(listBill.get(position).getB_start());
        holder.end.setText(listBill.get(position).getB_end());
        holder.f_cost.setText("\u20B9 "+String.valueOf(listBill.get(position).getB_fcost()));


    }
    @Override
    public int getItemCount() {
        Log.v(BillRecyclerAdapter.class.getSimpleName(),""+listBill.size());
        return listBill.size();
    }

    public class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView start,end,f_cost;
        OnBillListener obl;

        public BillViewHolder(final View view, OnBillListener onBillListener) {
            super(view);
            start = view.findViewById(R.id.s_date);
            end = view.findViewById(R.id.e_date);
            f_cost=view.findViewById(R.id.t_cost);
            obl=onBillListener;
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            monBillListener.onBillClick(getAdapterPosition());

        }
    }


    public interface OnBillListener{
        void onBillClick(int position);
    }



}
