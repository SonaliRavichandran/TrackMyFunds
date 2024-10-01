package com.example.myprojects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class LoginPage extends AppCompatActivity {

    EditText email,password;
    Button loginbutton,renderegister;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        loginbutton=(Button) findViewById(R.id.loginbutton);
        renderegister=(Button) findViewById(R.id.renderregister);
        dbhelper=new DatabaseHelper(this);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emai=email.getText().toString();
                String pass=password.getText().toString();
                if(emai.equals("") || pass.equals("")){
                    Toast.makeText(LoginPage.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkemailpass=dbhelper.checkusernamepassword(emai,pass);
                    if(checkemailpass==true){
                        Toast.makeText(LoginPage.this,"Login successful",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),Dashboard.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(LoginPage.this,"Invalid email and password",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        renderegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegisterPage.class);
                startActivity(i);

            }
        });


    }
}


