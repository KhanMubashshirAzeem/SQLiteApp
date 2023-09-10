package com.example.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateProfile extends AppCompatActivity {

    TextView  emailUpd;
    EditText nameUpd, genderUpd;
    UserModal userModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        emailUpd = (TextView) findViewById(R.id.emailUpd);
        nameUpd = (EditText) findViewById(R.id.nameUpd);
        genderUpd = (EditText) findViewById(R.id.genderUpd);

        userModal = (UserModal) getIntent().getSerializableExtra("key_usermodal");
        emailUpd.setText(userModal.getEmail());
        nameUpd.setText(userModal.getName());
        genderUpd.setText(userModal.getGender());
    }

    public void updateMyProfile(View view) {
        String nameUp = nameUpd.getText().toString();
        String genderUp = genderUpd.getText().toString();

        DBHelper dbHelper = new DBHelper(this);
        Boolean b = dbHelper.updateProfileHelper(userModal.getEmail(), nameUp , genderUp);

        if (b)
        {
            Toast.makeText(this, "Values updated successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show();
        }
    }



}