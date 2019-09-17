package com.example.revathy.assessmentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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
                email_str=email_et.getText().toString().trim();
                pwd_str=pwd_et.getText().toString().trim();
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}
