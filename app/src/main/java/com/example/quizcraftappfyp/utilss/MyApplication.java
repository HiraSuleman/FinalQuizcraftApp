package com.example.quizcraftappfyp.utilss;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyApplication extends Application
{
    public static DatabaseReference userDatabase,catDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        userDatabase=FirebaseDatabase.getInstance().getReference().child("Users");
        catDatabase=FirebaseDatabase.getInstance().getReference().child("Categories");

    }
}
