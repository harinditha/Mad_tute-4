package com.example.datahandling_tute4;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Database.DBHandler;

public class Login extends AppCompatActivity {

    EditText t_user, t_pass;
    Button btn_add;
    Button btn_signin;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t_user = (EditText) findViewById(R.id.editText);
        t_pass = (EditText) findViewById(R.id.editText2);
        btn_add = (Button) findViewById(R.id.button3);
        btn_signin = (Button) findViewById(R.id.button2);

        db = new DBHandler(this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String username = t_user.getText().toString().trim();
               // String pass = t_pass.getText().toString().trim();
                 String pass = t_user.getText().toString().trim();


                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(Login.this, "Enter your Username", Toast.LENGTH_SHORT).show();

                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "Enter your password", Toast.LENGTH_SHORT).show();

                }

                if (true) {
                    long val = db.addInfo(username, pass);
                    if (val != 0) {
                        Toast.makeText(Login.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    } else {
                        Toast.makeText(Login.this, "Registration Error", Toast.LENGTH_SHORT).show();

                    }


                }
            }

        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = t_user.getText().toString().trim();
                String pass = t_pass.getText().toString().trim();
                Boolean res = db.checkUser(user, pass);
                if (res == true) {
                    Toast.makeText(Login.this, "Succesfully logged In", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(Login.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}
