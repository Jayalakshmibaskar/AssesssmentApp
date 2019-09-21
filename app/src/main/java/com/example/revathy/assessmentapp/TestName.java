package com.example.revathy.assessmentapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestName extends AppCompatActivity {
    ListView test_name;

    ArrayList<Testmodel> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testname);
        test_name = findViewById(R.id.tests);
        list = new ArrayList<>();


        send();

        test_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent_reg = new Intent(TestName.this, SignUp.class);
                startActivity(intent_reg);
            }

        });

    }
    void send() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Ipaddress.BASE_URL + "test.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Log.e( "onResponse: ", response);
                        try {
                            JSONArray arr = new JSONArray(response);
                            list.clear();
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                list.add(new Testmodel(obj.getString("testname"), obj.getString("difficulty"), obj.getString("testid")));
                            }
                            adapter = new MyAdapter(list, TestName.this);
                            test_name.setAdapter(adapter);

                        } catch (JSONException e) {
                            Log.e( "error: ",e.toString() );
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(TestName.this, "error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );
        Volley.newRequestQueue(this).add(stringRequest);

    }


}

