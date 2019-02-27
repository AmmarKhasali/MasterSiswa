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
 * Created by USER on 07/02/2019.
 */

class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    // TODO: 2
    private  ArrayList<HashMap<String,String>> listData;
    private Context context;

    //todo : 1
    public StudentsAdapter(FragmentActivity context, ArrayList<HashMap<String, String>> listData) {
        // TODO : 3
        this.listData = listData;
        this.context = context;
    }





    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // memasukkan layout item student ke adapter
        // TODO : 4
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa,viewGroup,false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i) {
        // menampilkan data di komponen layout item student
        // TODO : 8
        studentViewHolder.txtname.setText(listData.get(i).get("nama"));
        studentViewHolder.txtalamat.setText(listData.get(i).get("alamat"));


    }

    @Override
    public int getItemCount() {
        // TODO : 5
        // menentukan jumlah item yang ingin di tampilkan
        return listData.size();
    }

    //untuk mendeklarasikan dan menginstall komponen layout item student
    public class StudentViewHolder extends RecyclerView.ViewHolder {
        // TODO : 6
        TextView txtname, txtalamat;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            // TODO : 7

            //inisiallisasi
            txtname = itemView.findViewById(R.id.item_namasiswa);
            txtalamat = itemView.findViewById(R.id.item_alamatsiswa);
        }
    }
}
