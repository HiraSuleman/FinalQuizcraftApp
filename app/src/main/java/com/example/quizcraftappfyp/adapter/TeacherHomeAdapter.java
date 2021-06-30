package com.example.quizcraftappfyp.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizcraftappfyp.R;

import java.util.List;

public class TeacherHomeAdapter  extends RecyclerView.Adapter<AdminHomeAdapter.ViewHolder> {
    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;
    public TeacherHomeAdapter(Context context, List<String> titels, List<Integer> images){
        this.titles=titels;
        this.images=images;
        this.inflater=LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public AdminHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.teacher_recycler_item,parent,false);
        return new AdminHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHomeAdapter.ViewHolder holder, int position) {
        holder.textView.setText(titles.get(position));
        holder.imageView.setImageResource(images.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
    public static  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
