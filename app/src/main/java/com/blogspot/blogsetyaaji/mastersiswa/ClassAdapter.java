package com.blogspot.blogsetyaaji.mastersiswa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by USER on 25/02/2019.
 */

class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private  ArrayList<HashMap<String,String>> listData;
    private Context context;

    public ClassAdapter(FragmentActivity context, ArrayList<HashMap<String, String>> listData) {
        this.listData = listData;
        this.context = context;

    }


    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class,viewGroup,false);
        return new ClassViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ClassViewHolder classViewHolder, int i) {
        classViewHolder.txtClass.setText(listData.get(i).get("class_name"));
        classViewHolder.txtTeacher.setText(listData.get(i).get("teacher_name"));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ClassViewHolder  extends RecyclerView.ViewHolder{
        TextView txtClass, txtTeacher;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            // TODO : 7

            //inisiallisasi
            txtClass = itemView.findViewById(R.id.item_class);
            txtTeacher = itemView.findViewById(R.id.item_teacher);
        }
    }
}
