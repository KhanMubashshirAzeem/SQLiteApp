package com.example.sqlitedatabasedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    TextView namePro, emailPro, genderPro;
    String email;
    UserModal userModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailPro = (TextView) findViewById(R.id.emailPro);
        namePro = (TextView) findViewById(R.id.namePro);
        genderPro = (TextView) findViewById(R.id.genderPro);

        email = getIntent().getStringExtra("key_email");

        getUserDetails();
    }

    public void logoutUser(View view) {
        startActivity(new Intent(Profile.this, Login.class));
    }

    public void getUserDetails() {
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<UserModal> al = dbHelper.getLoggedInUserDetail(email);
        userModal = al.get(0);

        namePro.setText(userModal.getName());
        emailPro.setText(userModal.getEmail());
        genderPro.setText(userModal.getGender());

        updateMyProfile();
    }


    public void updateProfile(View view) {
//        startActivity(new Intent(Profile.this , UpdateProfile.class));
        Intent intent = new Intent(Profile.this, UpdateProfile.class);
        intent.putExtra("key_usermodal", userModal);
        startActivity(intent);
    }

    public void updateMyProfile() {
        String nameUp = namePro.getText().toString();
        String genderUp = genderPro.getText().toString();
    }

    public void deleteProfile(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Profile");
        builder.setMessage("Are you sure you want to delete your profile?");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper dbHelper = new DBHelper(Profile.this);
                boolean b = dbHelper.deleteProfileHelper(userModal.getEmail());

                if (b) {
                    Toast.makeText(Profile.this, "Profile deleted successfully !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Profile.this, MainActivity.class));
                } else {
                    Toast.makeText(Profile.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.show();
    }
}