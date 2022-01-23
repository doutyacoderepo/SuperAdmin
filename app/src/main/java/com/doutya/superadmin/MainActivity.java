package com.doutya.superadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.doutya.superadmin.Retrofit.ApiClient;
import com.doutya.superadmin.Retrofit.GetApi;
import com.doutya.superadmin.Retrofit.Retrofit_Model;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    List<Retrofit_Model> ItemData = new ArrayList<>();

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

        GetJson();

        Next.setOnClickListener(View->{

        });

        AddCommunity.setOnClickListener(View->{
            SetCommunityOptionVisibility();
        });


    }
    private void GetJson() {

        GetApi api = ApiClient.getApiClient().create(GetApi.class);
        Call<List<Retrofit_Model>> call = api.GetTypeData();

        call.enqueue(new Callback<List<Retrofit_Model>>() {
            @Override
            public void onResponse(Call<List<Retrofit_Model>> call, Response<List<Retrofit_Model>> response) {
                Log.e("Retrofit onResponse", response.toString());

                ItemData = response.body();
                SetArrayAdapter();
            }

            @Override
            public void onFailure(Call<List<Retrofit_Model>> call, Throwable t) {
                Log.e("Retrofit error", t.getMessage());

            }
        });
    }


    private void SetArrayAdapter() {

        ArrayList<String>tempArray = new ArrayList<>();
        for(int i=0;i<ItemData.size();i++){
            Log.e("fdg",""+ItemData.get(i).getType());

            tempArray.add(""+ItemData.get(i).getType());
        }
        typeArray = tempArray.toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,typeArray);
        type.setThreshold(1);
        type.setAdapter(adapter);

        /*typeArray = new String[]{"Home", "Office", "College"};
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
        muncipality.setAdapter(adapter);*/
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