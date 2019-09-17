package com.example.revathy.assessmentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
TextView welcome,create;
EditText fname_edt,lname_edt,email_edt,newpwd_edt;
Button register_btn,already_btn;
String fname_str,lname_str,email_str,newpwd_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        welcome=findViewById(R.id.welcome);
        create=findViewById(R.id.create);
        fname_edt=findViewById(R.id.fname);
        lname_edt=findViewById(R.id.lname);
        email_edt=findViewById(R.id.email);
        newpwd_edt=findViewById(R.id.newpwd);
        register_btn=findViewById(R.id.register);
        already_btn=findViewById(R.id.already);
        already_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SignUp.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname_str=fname_edt.getText().toString().trim();
                lname_str=lname_edt.getText().toString().trim();
                email_str=email_edt.getText().toString().trim();
                newpwd_str=newpwd_edt.getText().toString().trim();
                if (Patterns.EMAIL_ADDRESS.matcher(email_str).matches()){
                    StringRequest str=new StringRequest(Request.Method.POST, Ipaddress.BASE_URL + "signup.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(SignUp.this,"Error"+error,Toast.LENGTH_SHORT).show();
                                }

                }) {
                        protected Map<String,String> getParams() throws AuthFailureError{
                            Map<String,String> params = new HashMap<>();
                            params.put("fname",fname_str);
                            params.put("lname",lname_str);
                            params.put("email",email_str);
                            params.put("pwd",newpwd_str);
                            return  params;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(SignUp.this);
                    queue.add(str);
                } else {
                    Toast.makeText(SignUp.this, "Enter valid id", Toast.LENGTH_LONG).show();

        };
    }
});
}
}
