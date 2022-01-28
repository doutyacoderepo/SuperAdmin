package com.doutya.superadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.doutya.superadmin.Retrofit.ApiClient;
import com.doutya.superadmin.Retrofit.GetApi;
import com.doutya.superadmin.Retrofit.PostApi;
import com.doutya.superadmin.Retrofit.PostModel;
import com.doutya.superadmin.Retrofit.Retrofit_Model;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{

    List<Retrofit_Model> ItemData = new ArrayList<>();

    AppCompatAutoCompleteTextView type,country,state,district;
    TextInputLayout muncipality, ward;
    AppCompatButton AddCommunity,Next;

    ArrayAdapter<String> adapter;
    String[] typeArray;
    boolean isVisible=false;
    String Type, Ward, Muncipality, Country, State, District, AddedOn, CommunityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommonFunctions functions = new CommonFunctions();

        type = findViewById(R.id.AutoTypeMain);
        country = findViewById(R.id.AutoCountryMain);
        state = findViewById(R.id.AutoStateMain);
        district = findViewById(R.id.AutoDistrictMain);
        muncipality = findViewById(R.id.MuncipalityETLayMain);
        ward = findViewById(R.id.wardETLayMain);
        Next = findViewById(R.id.NextBtnMain);
        AddCommunity = findViewById(R.id.AddCommunityMain);
        SetCommunityOptionVisibility();

        country.setOnItemSelectedListener(MainActivity.this);
        country.setOnItemClickListener(MainActivity.this);

        GetJson();
        GetJsonForCountry();
        GetJsonForState();
        GetJsonForDistrict();

        Next.setOnClickListener(View -> {
            if (functions.validate(type) && functions.validate(country)
                    && functions.validate(state) && functions.validate(district)
                    && functions.validate(muncipality) && functions.validate(ward)) {

                Type = functions.GetText(type);
                Country = functions.GetText(country);
                State = functions.GetText(state);
                District = functions.GetText(district);
                Muncipality = functions.GetText(muncipality);
                Ward = functions.GetText(ward);
                AddedOn = functions.currentDate();

                UploadNewCommunityData();

            }
        });

        AddCommunity.setOnClickListener(View -> {
            SetCommunityOptionVisibility();
        });


    }

    private void UploadNewCommunityData() {

        //Toast.makeText(MainActivity.this,Type+" "+Country+" "+State+" "+District+" "+Muncipality+" "+Ward+" "+ AddedOn, Toast.LENGTH_SHORT).show();

        PostApi apiInterface = ApiClient.getApiClient().create(PostApi.class);
        Call<PostModel> call = apiInterface.uploadCommunityData(Ward, Type, Muncipality, Country, State, District, AddedOn);
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                PostModel postModel = response.body();
                Toast.makeText(MainActivity.this, "Response:  " + postModel.getResponse(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SearchUser.class);
                intent.putExtra("communityId", postModel.getResponse());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure: " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void GetJson() {

        GetApi api = ApiClient.getApiClient().create(GetApi.class);
        Call<List<Retrofit_Model>> call = api.GetTypeData();

        call.enqueue(new Callback<List<Retrofit_Model>>() {
            @Override
            public void onResponse(Call<List<Retrofit_Model>> call, Response<List<Retrofit_Model>> response) {
                //   Log.e("Retrofit onResponse", response.toString());
                //   Log.e("Retrofit type", response.body().toString());
                ItemData = response.body();
                SetArrayAdapter();
            }

            @Override
            public void onFailure(Call<List<Retrofit_Model>> call, Throwable t) {
                //    Log.e("Retrofit error", t.getMessage());

            }
        });
    }

    private void SetArrayAdapter() {

        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < ItemData.size(); i++) {
            //Log.e("fdg",""+ItemData.get(i).getType());

            tempArray.add("" + ItemData.get(i).getType());
        }
        typeArray = tempArray.toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, typeArray);
        type.setThreshold(0);
        type.setAdapter(adapter);

    }

    private void GetJsonForCountry() {

        GetApi api = ApiClient.getApiClient().create(GetApi.class);
        Call<List<Retrofit_Model>> call = api.GetCountry();

        call.enqueue(new Callback<List<Retrofit_Model>>() {
            @Override
            public void onResponse(Call<List<Retrofit_Model>> call, Response<List<Retrofit_Model>> response) {

                ItemData = response.body();
                SetArrayAdapterForCountry();
            }

            @Override
            public void onFailure(Call<List<Retrofit_Model>> call, Throwable t) {

            }
        });
    }

    private void SetArrayAdapterForCountry() {


        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < ItemData.size(); i++) {

            tempArray.add("" + ItemData.get(i).getCountry());
        }
        typeArray = tempArray.toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, typeArray);
        country.setThreshold(1);
        country.setAdapter(adapter);

    }

    private void GetJsonForState() {

        GetApi api = ApiClient.getApiClient().create(GetApi.class);
        Call<List<Retrofit_Model>> call = api.GetState();

        call.enqueue(new Callback<List<Retrofit_Model>>() {
            @Override
            public void onResponse(Call<List<Retrofit_Model>> call, Response<List<Retrofit_Model>> response) {

                ItemData = response.body();
                SetArrayAdapterState();
            }

            @Override
            public void onFailure(Call<List<Retrofit_Model>> call, Throwable t) {
            }
        });
    }

    private void SetArrayAdapterState() {

        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < ItemData.size(); i++) {

            tempArray.add("" + ItemData.get(i).getState());
        }
        typeArray = tempArray.toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, typeArray);
        state.setThreshold(1);
        state.setAdapter(adapter);

    }

    private void GetJsonForDistrict() {

        GetApi api = ApiClient.getApiClient().create(GetApi.class);
        Call<List<Retrofit_Model>> call = api.GetDistrict();

        call.enqueue(new Callback<List<Retrofit_Model>>() {
            @Override
            public void onResponse(Call<List<Retrofit_Model>> call, Response<List<Retrofit_Model>> response) {
                ItemData = response.body();
                SetArrayAdapterDistrict();
            }

            @Override
            public void onFailure(Call<List<Retrofit_Model>> call, Throwable t) {

            }
        });
    }

    private void SetArrayAdapterDistrict() {

        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < ItemData.size(); i++) {

            tempArray.add("" + ItemData.get(i).getDistrict());
        }
        typeArray = tempArray.toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, typeArray);
        district.setThreshold(1);
        district.setAdapter(adapter);

    }

    private void SetCommunityOptionVisibility() {
        int v = View.GONE;
        if (isVisible) {
            v = View.VISIBLE;
            isVisible = false;
        } else
            isVisible = true;
        type.setVisibility(v);
        country.setVisibility(v);
        state.setVisibility(v);
        district.setVisibility(v);
        muncipality.setVisibility(v);
        ward.setVisibility(v);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Toast.makeText(getApplicationContext(), country.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Retrofit_Model retrofit_model = (Retrofit_Model) country.getAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), retrofit_model.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
