
package com.blogspot.blogsetyaaji.mastersiswa;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowStudentsFragment extends Fragment {


    public ShowStudentsFragment() {
// Required empty public constructor
    }

    private ArrayList<HashMap<String, String>> studentsData;
    //isi arraylist
/*/arraylist{
list {
nama,
nama
},
list {
nama,
nama
},
list {
nama,
nama
},
/*/
    private String url;
    TextView txtDnama;
    TextView txtDalamat;
    TextView txtDjson;



    RecyclerView lvStudent;






    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_show_students, container, false);
        RecyclerView lvStudent = view.findViewById(R.id.lv_student);

       // return inflater.inflate(R.layout.student_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvStudent = view.findViewById(R.id.lv_student);
        lvStudent.setLayoutManager(new LinearLayoutManager(getActivity()));
       // txtDnama = view.findViewById(R.id.txt_dnama);
        //txtDalamat = view.findViewById(R.id.txt_dalamat);
        //txtDjson= view.findViewById(R.id.ttxt_djson);

        studentsData = new ArrayList<HashMap<String, String>>();
        url = "http://192.168.70.134/smpidn/webdatabase/tampilsiswa2.php";
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
//Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                //txtDjson.setText(response.toString());
                //pilah data per item
                try {

                    //mengambil json array dgn nama "students"
                    JSONArray jsonArray = response.getJSONArray("siswa");

                    for (int a =0; a < jsonArray.length(); a++){

                        JSONObject jsonObject = jsonArray.getJSONObject(a);

                        HashMap<String, String> rowData = new HashMap<String, String>();
                        rowData.put("nama",jsonObject.getString("name"));
                        rowData.put("alamat",jsonObject.getString("address"));
                        studentsData.add(rowData);


                    }
                    StudentsAdapter studentsAdapter = new StudentsAdapter(getActivity(),studentsData);
                    /*/
                    txtDnama.setText(studentsData.get(1).get("nama"));
                    txtDalamat.setText(studentsData.get(1).get("alamat"));/*/
                    lvStudent.setAdapter(studentsAdapter);
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