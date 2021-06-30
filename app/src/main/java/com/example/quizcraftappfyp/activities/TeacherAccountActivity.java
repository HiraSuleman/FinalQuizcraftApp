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
import com.example.quizcraftappfyp.adapter.TeacherAccountAdapter;
import com.example.quizcraftappfyp.callback.ObjectCallback;
import com.example.quizcraftappfyp.models.TeacherAccountModel;
import com.example.quizcraftappfyp.utilss.MyApplication;
import com.example.quizcraftappfyp.utilss.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TeacherAccountActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
TeacherAccountAdapter adapter;
    FirebaseAuth mAuth;
    List<TeacherAccountModel> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);
        recyclerView=findViewById(R.id.recyclerview);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth=FirebaseAuth.getInstance();
        loadData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("floatingActionButton","floatingActionButton");

                Utils.createDialogs(TeacherAccountActivity.this, new ObjectCallback<TeacherAccountModel>() {
                    @Override
                    public void onData(TeacherAccountModel data) {
                        Utils.showDialog(TeacherAccountActivity.this);
                        Log.e("createDialog",""+data.email);
                        sigin(data);
                    }
                });
            }
        });
    }

    private void sigin(final TeacherAccountModel users) {
        mAuth.createUserWithEmailAndPassword(users.email,users.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e("createDialog","onComplete");
                Log.e("onComplete","onComplete"+task.getException());
                Utils.dissmiss();
                if(task.isSuccessful()){
                    users.uid=mAuth.getCurrentUser().getUid();
                    Log.e("Task","Successfull");
                    users.id=""+MyApplication.userDatabase.push().getKey();
                    MyApplication.userDatabase.child(users.id).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                list.clear();
                                loadData();
                                Toast.makeText(TeacherAccountActivity.this, "Teacher Created successfully", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(TeacherAccountActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }else {
                    Log.e("onComplete",""+task.getException());
                    Toast.makeText(TeacherAccountActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void loadData(){
        MyApplication.userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.e("onDataChange","onDataChange--------->"+snapshot.getChildrenCount());
                for (DataSnapshot s:snapshot.getChildren()) {
                  TeacherAccountModel value = s.getValue(TeacherAccountModel.class);
                    list.add(value);
                    Log.e("onDataChange",""+s.getKey());
                    Log.e("onDataChange",""+new Gson().toJson(value));
                }
                adapter=new TeacherAccountAdapter(list,TeacherAccountActivity.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}