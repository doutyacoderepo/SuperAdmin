package com.doutya.superadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    AppCompatAutoCompleteTextView type,country,state,district,muncipality;
    TextInputEditText ward;
    AppCompatButton AddCommunity,Next;
    ArrayAdapter<String> adapter;
    String[] typeArray;
    boolean isVisible=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        type = findViewById(R.id.AutoTypeMain);
        country = findViewById(R.id.AutoCountryMain);
        state = findViewById(R.id.AutoStateMain);
        district = findViewById(R.id.AutoDistrictMain);
        muncipality = findViewById(R.id.AutoMuncipalityMain);
        ward = findViewById(R.id.wardETMain);
        Next = findViewById(R.id.NextBtnMain);
        AddCommunity = findViewById(R.id.AddCommunityMain);
        SetCommunityOptionVisibility();

        Next.setOnClickListener(View->{
            startActivity(new Intent(this, SearchUserActivity.class));
        });

        AddCommunity.setOnClickListener(View->{
            SetCommunityOptionVisibility();
        });

        SetArrayAdapter();

    }

    private void SetArrayAdapter() {
        typeArray = new String[]{"Home", "Office", "College"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,typeArray);
        type.setThreshold(1);
        type.setAdapter(adapter);

        typeArray = new String[]{"Home", "Office", "College"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,typeArray);
        country.setThreshold(1);
        country.setAdapter(adapter);

        typeArray = new String[]{"Home", "Office", "College"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,typeArray);
        state.setThreshold(1);
        state.setAdapter(adapter);

        typeArray = new String[]{"Home", "Office", "College"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,typeArray);
        district.setThreshold(1);
        district.setAdapter(adapter);

        typeArray = new String[]{"Home", "Office", "College"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,typeArray);
        muncipality.setThreshold(1);
        muncipality.setAdapter(adapter);
    }

    private void SetCommunityOptionVisibility(){
        int v = View.GONE;
        if (isVisible){
           v = View.VISIBLE;
           isVisible=false;
        }else
            isVisible = true;
        type.setVisibility(v);
        country.setVisibility(v);
        state.setVisibility(v);
        district.setVisibility(v);
        muncipality.setVisibility(v);
        ward.setVisibility(v);

    }
}