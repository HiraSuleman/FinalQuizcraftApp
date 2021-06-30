package com.example.quizcraftappfyp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizcraftappfyp.R;
import com.example.quizcraftappfyp.adapter.TeacherHomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeacherHomeActivity extends AppCompatActivity {
    TeacherHomeAdapter teacherHomeAdapter;
    RecyclerView recyclerView;
    List<String> titles;
    List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        recyclerView = findViewById(R.id.recyclerview);
        titles = new ArrayList<>();
        images = new ArrayList<>();
        titles.add("Make quiz");
        titles.add("Annouce Result");
        titles.add("Create Groups");
        titles.add("Check Student Activites");
        titles.add("Generate Reports");
        titles.add("Create Schedule");
        images.add(R.drawable.ic_home);
        images.add(R.drawable.ic_home);
        images.add(R.drawable.ic_home);
        images.add(R.drawable.ic_home);
        images.add(R.drawable.ic_home);
        images.add(R.drawable.ic_home);

        teacherHomeAdapter=new TeacherHomeAdapter(this,titles,images);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(teacherHomeAdapter);

    }
}