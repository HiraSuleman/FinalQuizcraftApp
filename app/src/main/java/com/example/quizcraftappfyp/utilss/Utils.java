package com.example.quizcraftappfyp.utilss;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.callback.ObjectCallback;
import com.example.quizcraftappfyp.models.AdminAccountModel;
import com.example.quizcraftappfyp.models.TeacherAccountModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Utils {
    static ProgressDialog progressDialog;
    public  static void loadImages(Context context, final Activity activity, final int reqCode){
        Dexter.withContext(context)
                .withPermissions(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if(multiplePermissionsReport.areAllPermissionsGranted()){
                    Log.e("multiplePermissions","multiplePermissionsReport");
                    ArrayList<Image> list=new ArrayList<>();
                    ImagePicker.create(activity)
                            .limit(1)
                            .folderMode(true)
                            .origin(list)
                            .showCamera(true)
                            .start(reqCode);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
    public static void showDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        //progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dissmiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public static void createDialog(final Context context, final ObjectCallback<AdminAccountModel> callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_signup_layout, null, false);
        final EditText name = view.findViewById(R.id.name);
        final EditText email = view.findViewById(R.id.email);
        final EditText password = view.findViewById(R.id.password);
        final Button button = view.findViewById(R.id.button);
        final AdminAccountModel user = new AdminAccountModel();

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(context, "please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(context, "please Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(context, "please Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    Toast.makeText(context, "Password length must be 6 lenghth", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.email = email.getText().toString();
                user.name = name.getText().toString();
                user.password = password.getText().toString();
                callback.onData(user);
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public static void createDialogs(final Context context, final ObjectCallback<TeacherAccountModel> callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_signup_layout, null, false);
        final EditText name = view.findViewById(R.id.name);
        final EditText email = view.findViewById(R.id.email);
        final EditText password = view.findViewById(R.id.password);
        final Button button = view.findViewById(R.id.button);
        final TeacherAccountModel user = new TeacherAccountModel();

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(context, "please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(context, "please Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(context, "please Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    Toast.makeText(context, "Password length must be 6 lenghth", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.email = email.getText().toString();
                user.name = name.getText().toString();
                user.password = password.getText().toString();
                callback.onData(user);
                dialog.dismiss();

            }
        });
        dialog.show();
    }
    public static void editAdminDialogs(final AdminAccountModel user, final Activity context, final ObjectCallback<AdminAccountModel> callback) {
        View view = LayoutInflater.from(context).inflate(R.layout.edit_admin_signup_layout, null, false);
        final EditText name = view.findViewById(R.id.name);
        final EditText email = view.findViewById(R.id.email);
        final CircleImageView profileImage = view.findViewById(R.id.profileImage);
        final EditText password = view.findViewById(R.id.password);
        final Button button = view.findViewById(R.id.button);

        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();

name.setText(""+user.name);
email.setText(""+user.email);
        password.setText(""+user.password);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseHelper.adminAccountModel=user;
                loadImages(context,context,1001);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(context, "please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(context, "please Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(context, "please Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    Toast.makeText(context, "Password length must be 6 lenghth", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.email = email.getText().toString();
                user.name = name.getText().toString();
                user.password = password.getText().toString();
                callback.onData(user);
                dialog.dismiss();

            }
        });
        dialog.show();
    }
    public static void showImge(Context context,String path, ImageView imageView){
        //Picasso.get().load(path).into(imageView);
        Glide.with(context).load(path).placeholder(R.drawable.logo).into(imageView);
    }
}
