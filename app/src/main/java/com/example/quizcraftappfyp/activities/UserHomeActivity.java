package com.example.quizcraftappfyp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.fragments.DashboardFregment;
import com.example.quizcraftappfyp.fragments.HomeFregment;
import com.example.quizcraftappfyp.fragments.MyAccountFregment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHomeActivity extends AppCompatActivity   {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.home:
                        HomeFregment homeFragment=new HomeFregment();
                        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout,homeFragment);
                        fragmentTransaction.commit();


                        break;
                    case R.id.dashboard:

                        DashboardFregment dashboardFragment=new DashboardFregment();
                        FragmentTransaction fragmentTransaction1=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame_layout,dashboardFragment);
                        fragmentTransaction1.commit();

                        break;
                    case R.id.myaccount:
                        MyAccountFregment settingFragment=new MyAccountFregment();
                        FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame_layout,settingFragment);
                        fragmentTransaction2.commit();

                        break;

                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
}