package com.example.quizcraftappfyp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.callback.ObjectCallback;
import com.example.quizcraftappfyp.models.AdminAccountModel;
import com.example.quizcraftappfyp.models.TeacherAccountModel;
import com.example.quizcraftappfyp.utilss.BaseHelper;
import com.example.quizcraftappfyp.utilss.Utils;

import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.MyViewHlder> {
    List<AdminAccountModel> list;
    Activity context;

    public AdminUserAdapter(List<AdminAccountModel> list, Activity context) {
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
    public void onBindViewHolder(@NonNull MyViewHlder holder, int position) {
        final AdminAccountModel user = list.get(position);
        holder.tvName.setText(""+user.name);
        holder.tvEmail.setText(""+user.email);
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///code here for new Activity
                BaseHelper.adminAccountModel=user;
                Utils.editAdminDialogs(user,context, new ObjectCallback<AdminAccountModel>() {
                    @Override
                    public void onData(AdminAccountModel data) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder {
        TextView tvName,tvEmail;
        ImageView ivEdit;
        public MyViewHlder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvEmail=itemView.findViewById(R.id.tvEmail);
            ivEdit=itemView.findViewById(R.id.ivEdit);
        }
    }
}
