package com.example.revathy.assessmentapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    EditText email_et,pwd_et;
    Button login_btn,signup_btn;
    String email_str,pwd_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_et=findViewById(R.id.mail);
        pwd_et=findViewById(R.id.password);
        login_btn=findViewById(R.id.login);
        signup_btn=findViewById(R.id.signup);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_str=email_et.getText().toString().trim();
                pwd_str=pwd_et.getText().toString().trim();
                String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
                Matcher matcher = pattern.matcher(pwd_str);
                if (Patterns.EMAIL_ADDRESS.matcher(email_str).matches() && matcher.matches()) {
                    ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
                    pdLoading.setMessage("Loading");
                    StringRequest sr1 = new StringRequest(Request.Method.POST, Ipaddress.BASE_URL +"login.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try{

                                        Log.e( "onResponse: ",response );
                                     JSONObject jsonObject=new JSONObject(response);
                                     String error=jsonObject.getString("error");
                                     if(error.equals("false")){
                                         Intent intent1=new Intent(MainActivity.this,TestName.class);
                                         startActivity(intent1);
                                     }
                                     else {
                                         Toast.makeText(MainActivity.this,"Username does not exists",Toast.LENGTH_SHORT).show();
                                     }
                                    }
                                     catch (JSONException e){
                                        e.printStackTrace();
                                     }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("email", email_str);
                            params.put("pwd",pwd_str);

                            return params;
                        }

                    };
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(sr1);    }
            else{
                    Toast.makeText(MainActivity.this,"Enter valid data",Toast.LENGTH_SHORT).show();
                }


                }
        });
    }

    }
