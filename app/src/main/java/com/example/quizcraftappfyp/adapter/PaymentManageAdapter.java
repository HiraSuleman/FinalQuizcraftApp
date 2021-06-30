package com.example.quizcraftappfyp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.models.PaymentManageModel;

import java.util.List;

public class PaymentManageAdapter extends RecyclerView.Adapter<PaymentManageAdapter.MyViewHlder> {
    List<PaymentManageModel> list;
    Context context;

    public PaymentManageAdapter(List<PaymentManageModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.payment_manage_list_item, parent, false);
        return new MyViewHlder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHlder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyViewHlder extends RecyclerView.ViewHolder {
        public MyViewHlder(@NonNull View itemView) {
            super(itemView);
        }
    }
}



