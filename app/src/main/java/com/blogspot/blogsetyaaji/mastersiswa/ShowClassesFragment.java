package com.blogspot.blogsetyaaji.mastersiswa;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ShowClassesFragment extends Fragment {

    public ShowClassesFragment() {
        // Required empty public constructor
    }
    private ArrayList<HashMap<String, String>> ClassData;


    private String url;


    RecyclerView lvClass;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_show_classes, container, false);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvClass = view.findViewById(R.id.lv_class);
        lvClass.setLayoutManager(new LinearLayoutManager(getActivity()));

        ClassData = new ArrayList<HashMap<String, String>>();
        url = "http://192.168.70.134/SMPIDN/webdatabase/classes.php";
        showData();


    }

    private void showData() {
//menampilkan proses request berupa progres dialog
        final ProgressDialog progressDialaog = new ProgressDialog(getActivity());
        progressDialaog.setTitle("Loading");
        progressDialaog.setMessage("Please Wait..");
        progressDialaog.show();

//membuat Request
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
//data disimpan di dalam response
            public void onResponse(JSONObject response) {
                progressDialaog.dismiss();
                //pilah data per item
                try {

                    //mengambil json array dgn nama "students"
                    JSONArray jsonArray = response.getJSONArray("class");

                    for (int a =0; a < jsonArray.length(); a++){

                        JSONObject jsonObject = jsonArray.getJSONObject(a);

                        HashMap<String, String> rowData = new HashMap<String, String>();
                        rowData.put("class_name",jsonObject.getString("class_name"));
                        rowData.put("id_teacher",jsonObject.getString("teacher_id"));
                        ClassData.add(rowData);


                    }
                    ClassAdapter classAdapter = new ClassAdapter(getActivity(),ClassData);
                    /*/
                    txtDnama.setText(studentsData.get(1).get("nama"));
                    txtDalamat.setText(studentsData.get(1).get("alamat"));/*/
                    lvClass.setAdapter(classAdapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
//menjalankan Request
        RequestQueue myrequestqueue = Volley.newRequestQueue(getActivity());
        myrequestqueue.add(myRequest);

    }



}
