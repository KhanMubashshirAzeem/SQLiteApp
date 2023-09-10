package com.example.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText emailLog, passwordLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLog = (EditText) findViewById(R.id.emailLog);
        passwordLog = (EditText) findViewById(R.id.passwordLog);


    }

    public void loginUser(View view) {
        String password = passwordLog.getText().toString();
        String email = emailLog.getText().toString();

//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
//            return; // Exit the function to prevent further execution
//        }

        DBHelper dbHelper = new DBHelper(this);
        boolean loggedIn = dbHelper.login(email, password);
        if (loggedIn) {
            Intent intent = new Intent(Login.this, Profile.class);
            intent.putExtra("key_email", email);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Email Id and password didn't matched", Toast.LENGTH_SHORT).show();
        }

    }
}