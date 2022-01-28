package com.doutya.superadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.doutya.superadmin.Adapter.SearchAdapter;
import com.doutya.superadmin.Helper.ApiClient;
import com.doutya.superadmin.Helper.PostApi;
import com.doutya.superadmin.Models.AdminNotifyModel;
import com.doutya.superadmin.Models.SearchUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchUserActivity extends AppCompatActivity {

    TextInputLayout et;
    List<SearchUser> searchUserArrayList = new ArrayList<>();
    List<SearchUser> EmptyArray = new ArrayList<>();
    RecyclerView recyclerView;
    FirebaseFirestore db;
    SearchAdapter mAdapter;
    String token=null;
    AppCompatButton addAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        recyclerView = findViewById(R.id.RecyclerSearchUser);
        et = findViewById(R.id.search_user_Et);
        addAdmin = findViewById(R.id.AddSelectedUserS);

        db = FirebaseFirestore.getInstance();
        buildRecyclerView();
        GetUserList();


        et.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        addAdmin.setOnClickListener(View->{
            UploadData();
        });



    }


    private void UploadData(){
        Log.e("ClickResponse:", "clicked");
        PostApi apiInterface = ApiClient.getApiClient().create(PostApi.class);

        Call<AdminNotifyModel> call = apiInterface.NotifyAdmin(token);
        Log.e("Server token", token);
        call.enqueue(new Callback<AdminNotifyModel>() {
            @Override
            public void onResponse(Call<AdminNotifyModel> call, Response<AdminNotifyModel> response) {

                AdminNotifyModel img_pojo = response.body();
                Toast.makeText(SearchUserActivity.this,"Response:  "
                        + img_pojo.getResponse() , Toast.LENGTH_SHORT).show();
                Log.e("Server Response", "" + img_pojo.getResponse());

            }

            @Override
            public void onFailure(Call<AdminNotifyModel> call, Throwable t) {
                Toast.makeText(SearchUserActivity.this,  "Failure: " +
                        t.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Server Response", "" + t.toString());

            }
        });
    }



    private void filter(String text) {
        List<SearchUser> filteredList = new ArrayList<>();

        if (text.isEmpty() || text.length()==10){
            mAdapter.filterList(EmptyArray);
            View v = this.getCurrentFocus();
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(v.getWindowToken(),0);
            return;
        }
        for (SearchUser item : searchUserArrayList) {
            if (item.getContact_Number().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }



    private void buildRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        mAdapter = new SearchAdapter(this,EmptyArray);
        recyclerView.setAdapter(mAdapter);
    }



    private void GetUserList() {
        db.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()){
                    return;
                }
                Log.e("datass",""+queryDocumentSnapshots.size());
                searchUserArrayList = queryDocumentSnapshots.toObjects(com.doutya.superadmin.Models.SearchUser.class);
                for (int i = 0; i < searchUserArrayList.size(); i++) {
                    Log.e("datass", searchUserArrayList.get(i).getName());
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("datass",e.getMessage());
            }
        });
    }

    public void SetSelectedItem(String s, String token){
        et.getEditText().setText(s);
        this.token =token;
    }
}