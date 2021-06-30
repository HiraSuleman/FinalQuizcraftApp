package com.example.quizcraftappfyp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.models.GradeClassModel;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.MyViewHlder> {
    List<GradeClassModel> list;
    Context context;

    public ClassAdapter(List<GradeClassModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.admin_account_list_item, parent, false);
        return new MyViewHlder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHlder holder, final int position) {
        GradeClassModel user = list.get(position);
        holder.tvName.setText(""+user.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder {
        TextView tvName;
        public MyViewHlder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
        }
    }
}
