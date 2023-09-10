package com.example.sqlitedatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "demoDb";
    private static final int DATABASE_VERSION = 1;
    boolean loggedIn;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE register(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT , gender TEXT)";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS register");
        onCreate(db);
    }

    public boolean registerUserHelper(String name1, String email1, String password1, String gender1) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name1);
        contentValues.put("email", email1);
        contentValues.put("password", password1);
        contentValues.put("gender", gender1);

        long l = sqLiteDatabase.insert("register", null, contentValues);
        sqLiteDatabase.close();

        return l > 0;
    }

    public boolean login(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM register WHERE email='" + email + "' AND password='" + password + "'", null);
        loggedIn = cursor.moveToFirst();
        return loggedIn;
    }

    public ArrayList<UserModal> getLoggedInUserDetail(String emailPro) {

        ArrayList<UserModal> al = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM register WHERE email = '" + emailPro + "' ";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String gender = cursor.getString(4);

            UserModal userModal = new UserModal();
            userModal.setName(name);
            userModal.setEmail(email);
            userModal.setGender(gender);

            al.add(userModal);

        }
        return al;
    }

    public boolean updateProfileHelper(String emailUp, String nameUp, String genderUp) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", nameUp);
        contentValues.put("gender", genderUp);

        int i = sqLiteDatabase.update("register", contentValues, "email=?", new String[]{emailUp});
        return i > 0;
    }

    public boolean deleteProfileHelper(String email)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int i = sqLiteDatabase.delete("register" ,"email=?", new String[]{email});

        if (i>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkEmailIdExist(String email)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM register WHERE email='"+email+"' " , null);

        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }

    }
}
