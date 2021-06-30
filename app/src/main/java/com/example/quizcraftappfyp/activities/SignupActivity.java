package com.example.quizcraftappfyp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText etEmail, etPassword, etName;
    UserModel userModel;
    RadioButton radioButton1, radioButton2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference usersRef;
    Button btnSignup, btnlogin;
    FirebaseAuth mAuth;
    String name, email, password;
    String designation;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        firebaseDatabase = FirebaseDatabase.getInstance();
        usersRef = firebaseDatabase.getReference("Users");
        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        radioButton1 = findViewById(R.id.radiobutton1);
        radioButton2 = findViewById(R.id.radiobutton2);
        btnSignup = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();
        btnlogin = findViewById(R.id.login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button:
                        name = etName.getText().toString();
                        email = etEmail.getText().toString();
                        password = etPassword.getText().toString();
                        String m1 = radioButton1.getText().toString();
                        String m2 = radioButton2.getText().toString();


                        if (radioButton1.isChecked()) {

                            designation = m1;
                          usersRef.child(String.valueOf(i + 1)).setValue(userModel);
                        } else {

                            designation = m2;
                            usersRef.child(String.valueOf(i + 1)).setValue(userModel);

                            if (name.isEmpty()) {
                                etName.setError("Field required");
                                return;
                            }
                            if (email.isEmpty()) {
                                etEmail.setError("Field required");
                                return;
                            }
                            if (password.isEmpty()) {
                                etPassword.setError("Field required");
                                return;
                            }


                            try {
                                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                              userModel= new UserModel(name, email, firebaseUser.getUid(), designation, false, false);
                                            usersRef.child(firebaseUser.getUid()).setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                                        builder.setTitle("Task Successfull");
                                                        builder.setMessage(userModel.getName() + " created an account !");
                                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                                                Toast.makeText(SignupActivity.this, "sign up successfull", Toast.LENGTH_LONG).show();
                                                            }
                                                        });
                                                        AlertDialog alertDialog = builder.create();
                                                        alertDialog.show();
                                                    }
                                                }
                                            });

                                        }
                                    }
                                });
                            } catch (Exception e) {

                            }
                        }

                            break;

                            case R.id.login:
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                break;

                }
            }
        });
    }
}









