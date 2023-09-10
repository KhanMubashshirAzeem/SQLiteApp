package com.example.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLoginActivity(View view) {
        Intent intent = new Intent(MainActivity.this , Login.class);
        startActivity(intent);
    }

    public void openRegisterActivity(View view) {
        startActivity(new Intent(MainActivity.this , Register.class));
    }
}