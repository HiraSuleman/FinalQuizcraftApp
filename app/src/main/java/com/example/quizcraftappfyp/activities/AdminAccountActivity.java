package com.example.quizcraftappfyp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.adapter.AdminUserAdapter;
import com.example.quizcraftappfyp.callback.ObjectCallback;
import com.example.quizcraftappfyp.models.AdminAccountModel;
import com.example.quizcraftappfyp.utilss.BaseHelper;
import com.example.quizcraftappfyp.utilss.MyApplication;
import com.example.quizcraftappfyp.utilss.MyConstants;
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

import static com.esafirm.imagepicker.features.ImagePicker.getImages;

public class AdminAccountActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView profile;
    FloatingActionButton floatingActionButton;
    AdminUserAdapter adapter;
    FirebaseAuth mAuth;
    List<AdminAccountModel> list=new ArrayList<>();
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

                Utils.createDialog(AdminAccountActivity.this, new ObjectCallback<AdminAccountModel>() {
                    @Override
                    public void onData(AdminAccountModel data) {
                        Utils.showDialog(AdminAccountActivity.this);
                        Log.e("createDialog",""+data.email);
                        sigin(data);
                    }
                });
            }
        });
    }

    private void sigin(final AdminAccountModel user) {
        mAuth.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e("createDialog","onComplete");
                Log.e("onComplete","onComplete"+task.getException());
                Utils.dissmiss();
                if(task.isSuccessful()){
                    user.uid=mAuth.getCurrentUser().getUid();
                    Log.e("Task","Successfull");
                    user.id=""+MyApplication.userDatabase.push().getKey();
                    MyApplication.userDatabase.child(user.id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                list.clear();
                                loadData();
                                Toast.makeText(AdminAccountActivity.this, "AdminCreated successfully", Toast.LENGTH_SHORT).show();
                            }else {
                                Log.e("onComplete",""+task.getException());

                                Toast.makeText(AdminAccountActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }else {
                    Log.e("onComplete",""+task.getException());
                    Toast.makeText(AdminAccountActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
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
                    AdminAccountModel value = s.getValue(AdminAccountModel.class);
                    list.add(value);
                    Log.e("onDataChange",""+s.getKey());
                    Log.e("onDataChange",""+new Gson().toJson(value));
                }
                adapter=new AdminUserAdapter(list,AdminAccountActivity.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== MyConstants.IMAGE_REQUEST && data!=null){
            List<Image> images = getImages(data);
            Image image=images.get(0);
            BaseHelper.adminAccountModel.profileUrl=image.getPath();
            Log.e("path",""+BaseHelper.adminAccountModel.profileUrl);
            
        }
    }
}