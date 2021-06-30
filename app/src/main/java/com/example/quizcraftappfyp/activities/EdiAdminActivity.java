package com.example.quizcraftappfyp.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.esafirm.imagepicker.model.Image;
import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.models.AdminAccountModel;
import com.example.quizcraftappfyp.utilss.BaseHelper;
import com.example.quizcraftappfyp.utilss.MyConstants;
import com.example.quizcraftappfyp.utilss.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.esafirm.imagepicker.features.ImagePicker.getImages;

public class EdiAdminActivity extends AppCompatActivity {
    AdminAccountModel user;
    CircleImageView profileImage;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_admin_signup_layout);
        user= BaseHelper.adminAccountModel;
        final EditText name = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);

        profileImage = findViewById(R.id.profileImage);
        final EditText password =findViewById(R.id.password);
        final Button button = findViewById(R.id.button);


        name.setText(""+user.name);
        email.setText(""+user.email);
        password.setText(""+user.password);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.loadImages(EdiAdminActivity.this,EdiAdminActivity.this,1001);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(EdiAdminActivity.this, "please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(EdiAdminActivity.this, "please Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(EdiAdminActivity.this, "please Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    Toast.makeText(EdiAdminActivity.this, "Password length must be 6 lenghth", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.email = email.getText().toString();
                user.name = name.getText().toString();
                user.password = password.getText().toString();

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
            path=image.getPath();
            Utils.showImge(EdiAdminActivity.this,image.getPath(),profileImage);
            Log.e("path",""+BaseHelper.adminAccountModel.profileUrl);

        }
    }
}