package com.example.quizcraftappfyp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 101;
    EditText etEmail, etPassword;
    Button btnLogin,btnjoinus;
    TextView tvforgetpassword;
    String password, email;
    DatabaseReference usersRef;
    FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
         if (user != null&& SplashActivity.currentUser.getUid()!=null) {
            if (SplashActivity.currentUser != null) {
                if (SplashActivity.currentUser.isAdmin()) {
                    startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class));
                }
                else if (SplashActivity.currentUser.isTeacher()){
                    startActivity(new Intent(LoginActivity.this, TeacherHomeActivity.class));

                }
                else{
                    startActivity(new Intent(LoginActivity.this, UsersHomeActivity.class));

                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        tvforgetpassword = findViewById(R.id.forget);
        btnLogin = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference("Users");
        btnjoinus = findViewById(R.id.joinus);
        btnjoinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
      
        tvforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecoveryPasswordDialoug();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.button:
                        email = etEmail.getText().toString();
                        password = etPassword.getText().toString();

//                }
                if (email.isEmpty()) {
                    etEmail.setError("Field required");
                    return;
                }
                if (password.isEmpty()) {
                    etPassword.setError("Field required");
                    return;
                }
                try {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uid = mAuth.getCurrentUser().getUid();
                                usersRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        try {
                                            UserModel userModel = snapshot.getValue(UserModel.class);
                                            if (userModel.isAdmin()) {
                                                startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class));
                                                Toast.makeText(LoginActivity.this, "Sucessfully login", Toast.LENGTH_SHORT).show();
                                            }
                                            else if (userModel.isTeacher()){
                                                startActivity(new Intent(LoginActivity.this, TeacherHomeActivity.class));
                                                Toast.makeText(LoginActivity.this, "Sucessfully login", Toast.LENGTH_SHORT).show();


                                            }
                                            else {
                                                startActivity(new Intent(LoginActivity.this, UsersHomeActivity.class));
                                                Toast.makeText(LoginActivity.this, "Sucessfully login", Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (Exception e) {
                                            Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setTitle("Failed");
                                builder.setMessage(task.getException().getMessage());
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                       // Toast.makeText(LoginActivity.this, "Sucessfully created", Toast.LENGTH_SHORT).show();
                                       // startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }
                    });
                } catch (Exception e) {
                }


            }

        });
    }



    private void showRecoveryPasswordDialoug() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText etEmail=new EditText(this);
        etEmail.setHint("Email");
        etEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        etEmail.setMinEms(16);
        linearLayout.addView(etEmail);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email=etEmail.getText().toString().trim();
                beingRecovery(email);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        builder.create().show();
    }

    private void beingRecovery(String email) {

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"Email Sent",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(LoginActivity.this,"Failed...",Toast.LENGTH_SHORT);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
