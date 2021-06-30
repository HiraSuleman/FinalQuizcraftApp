package com.example.quizcraftappfyp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.models.TeacherAccountModel;

import java.util.List;

public class TeacherAccountAdapter extends RecyclerView.Adapter<TeacherAccountAdapter.MyViewHlder> {
    List<TeacherAccountModel> list;
    Context context;

    public TeacherAccountAdapter(List<TeacherAccountModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.teacher_account_list_item, parent, false);
        return new MyViewHlder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHlder holder, int position) {
        TeacherAccountModel user = list.get(position);
        holder.tvName.setText("" + user.name);
        holder.tvEmail.setText("" + user.email);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail;

        public MyViewHlder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}


