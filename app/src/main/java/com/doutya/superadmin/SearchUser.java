package com.doutya.superadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.doutya.superadmin.Retrofit.ApiClient;
import com.doutya.superadmin.Retrofit.GetApi;
import com.doutya.superadmin.Retrofit.PostApi;
import com.doutya.superadmin.Retrofit.PostModel;
import com.doutya.superadmin.Retrofit.UserModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchUser extends AppCompatActivity {

    List<UserModel> UserData = new ArrayList<>();
    String[] typeArray;
    ArrayAdapter<String> adapter;

    AppCompatAutoCompleteTextView searchUser;
    AppCompatButton ButtonAdd;

    String CommunityID,ContactNumber;

    CommonFunctions functions = new CommonFunctions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);


        searchUser = findViewById(R.id.AutoSearchUser);
        ButtonAdd = findViewById(R.id.ButtonAddAdmin);


            CommunityID = getIntent().getStringExtra("communityId");
            Log.e("search",CommunityID+" ");


        ButtonAdd.setOnClickListener(view -> {
            ContactNumber = searchUser.getText().toString();
            UploadPendingApproval();
        });



        GetJson();

    }

    private void UploadPendingApproval() {
        PostApi apiInterface = ApiClient.getApiClient().create(PostApi.class);
        Call<PostModel> call = apiInterface.uploadAdminPendingApproval(ContactNumber,CommunityID,functions.currentDate());
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                PostModel postModel = response.body();
                Toast.makeText(SearchUser.this,"Response:  " + postModel.getResponse().length() , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(SearchUser.this,  "Failure: " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void GetJson() {
        GetApi Api = ApiClient.getApiClient().create(GetApi.class);
        Call<List<UserModel>> call = Api.GetPhoneNumber();

        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {

                UserData =response.body();
               // Log.e("Retrofit onResponse", response.body().get(0).getPhone());
                SetArrayAdapter();

            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
               // Log.e("Retrofit error", t.getMessage());
            }
        });
    }

    private void SetArrayAdapter() {
        ArrayList<String>tempArray = new ArrayList<>();
        for(int i=0;i<UserData.size();i++){
           // Log.e("fdg",""+UserData.get(i).getPhone());

            tempArray.add(""+UserData.get(i).getPhone());
          //  Log.e("tempArray",""+tempArray);
        }
        typeArray = tempArray.toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,typeArray);
        searchUser.setThreshold(1);
        searchUser.setAdapter(adapter);
    }
}