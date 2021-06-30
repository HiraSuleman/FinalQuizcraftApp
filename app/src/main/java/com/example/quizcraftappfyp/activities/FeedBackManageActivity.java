package com.example.quizcraftappfyp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.adapter.FeedBackManageAdapter;
import com.example.quizcraftappfyp.models.FeedBackManageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FeedBackManageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    FeedBackManageAdapter adapter;
    FirebaseAuth mAuth;
    List<FeedBackManageModel> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_manage);
        recyclerView=findViewById(R.id.recyclerview);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        adapter=new FeedBackManageAdapter(list,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mAuth= FirebaseAuth.getInstance();
        final FeedBackManageModel user=new FeedBackManageModel();
        user.email="aa@bbb.com";
        user.name="test";
        user.password="123456";
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigin(user);
            }
        });
    }

    private void sigin(final FeedBackManageModel user) {
        mAuth.signInWithEmailAndPassword(user.email,user.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e("onComplete","onComplete"+task.getException());

                if(task.isSuccessful()){
                    user.uid=mAuth.getCurrentUser().getUid();
                    Log.e("Task","Successfull");
                }else {
                    Log.e("onComplete",""+task.getException());
                    Toast.makeText(FeedBackManageActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

