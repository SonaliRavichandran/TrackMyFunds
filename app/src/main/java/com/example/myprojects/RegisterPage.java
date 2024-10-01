package com.example.myprojects;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class RegisterPage extends AppCompatActivity {


    EditText username,password,repassword,email;
    Button sigupbutton,renderlogin;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        email=(EditText) findViewById(R.id.email);
        sigupbutton=(Button) findViewById(R.id.signupbutton);
        renderlogin=(Button) findViewById(R.id.renderlogin);
        dbhelper=new DatabaseHelper(this);

        sigupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();
                String ema=email.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("") || ema.equals(""))
                {
                    Toast.makeText(RegisterPage.this,"please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)) {
                        Boolean checkuser = dbhelper.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = dbhelper.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(RegisterPage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), LoginPage.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegisterPage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterPage.this, "User already exists!Please login", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterPage.this,"Password not matching",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        renderlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(i);
            }
        });




    }
}