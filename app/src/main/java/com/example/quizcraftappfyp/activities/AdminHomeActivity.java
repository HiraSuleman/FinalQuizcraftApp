package com.example.quizcraftappfyp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.adapter.AdminHomeAdapter;
import com.example.quizcraftappfyp.callback.ObjectCallback;

import java.util.ArrayList;
import java.util.List;

public class AdminHomeActivity extends AppCompatActivity  {
    AdminHomeAdapter adminHomeAdapter;
    RecyclerView recyclerView;
    List<String> titles;
    List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        recyclerView = findViewById(R.id.recyclerview);
        titles = new ArrayList<>();
        images = new ArrayList<>();
        titles.add("Manage Admin");
        titles.add("Manage Teacher");
        titles.add("Manage Student");
        titles.add("Manage Class");
        titles.add("Manage Subject");
        titles.add("Payments");
        titles.add("Feed Backs");
        titles.add("Settings");
        images.add(R.drawable.admin);
        images.add(R.drawable.teacher);
        images.add(R.drawable.students);
        images.add(R.drawable.classroom);
        images.add(R.drawable.subjects);
        images.add(R.drawable.payment);
        images.add(R.drawable.feedback);
        images.add(R.drawable.settings);


        adminHomeAdapter=new AdminHomeAdapter(this, titles, images, new ObjectCallback<Integer>() {
            @Override
            public void onData(Integer data) {
                if(data==0){
                    startActivity(new Intent(AdminHomeActivity.this,AdminAccountActivity.class));
                }
                if(data==1){
                    startActivity(new Intent(AdminHomeActivity.this,TeacherAccountActivity.class));
                }
                if(data==2){
                    startActivity(new Intent(AdminHomeActivity.this,StudentAccountActivity.class));
                }
                if(data==3){
                    startActivity(new Intent(AdminHomeActivity.this,AddClassActivity.class));
                }
                if(data==4){
                    startActivity(new Intent(AdminHomeActivity.this,TeacherAccountActivity.class));
                }

                if(data==5){
                    startActivity(new Intent(AdminHomeActivity.this,PaymentManageActivity.class));
                }
                if(data==6){
                    startActivity(new Intent(AdminHomeActivity.this,FeedBackManageActivity.class));
                }
                if(data==7){
                    startActivity(new Intent(AdminHomeActivity.this,TeacherAccountActivity.class));
                }
                if(data==8){
                    startActivity(new Intent(AdminHomeActivity.this,SettingActivity.class));
                }

            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adminHomeAdapter);






    }





}

