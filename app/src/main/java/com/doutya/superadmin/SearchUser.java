package com.doutya.superadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

public class SearchUser extends AppCompatActivity {

    TextInputLayout et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        et = findViewById(R.id.search_user_Et);


    }
}