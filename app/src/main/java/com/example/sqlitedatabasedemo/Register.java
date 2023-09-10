package com.example.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {


    EditText nameReg , emailReg , passwordReg , genderReg;
    Button buttonReg;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameReg = (EditText) findViewById(R.id.nameReg);
        emailReg = (EditText) findViewById(R.id.emailReg);
        passwordReg = (EditText) findViewById(R.id.passwordReg);
        genderReg = (EditText) findViewById(R.id.genderReg);
        buttonReg = (Button) findViewById(R.id.buttonReg);

        dbHelper = new DBHelper(getApplicationContext());

    }

    public void registerUser(View view) {
        String name = nameReg.getText().toString();
        String email = emailReg.getText().toString();
        String password = passwordReg.getText().toString();
        String gender = genderReg.getText().toString();

        boolean b1 =dbHelper.checkEmailIdExist(email);

        if (b1)
        {
            Toast.makeText(this, "Email id already exists", Toast.LENGTH_SHORT).show();
        }
        else {

            boolean b = dbHelper.registerUserHelper(name , email , password , gender);
            if (b)
            {
                Toast.makeText(this, "User registered successfully...........", Toast.LENGTH_SHORT).show();

            nameReg.setText("");
            emailReg.setText("");
            passwordReg.setText("");
            genderReg.setText("");
            }
            else
            {
                Toast.makeText(this, "Error..............", Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "Now you can login", Toast.LENGTH_SHORT).show();
        }


    }
}