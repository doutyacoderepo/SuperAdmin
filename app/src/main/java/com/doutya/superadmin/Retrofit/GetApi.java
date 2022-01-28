package com.doutya.superadmin.Retrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetApi {

        //Add Community

    @GET("getCommunityType.php")
    @Headers("Cache-Control: no-cache")
    Call<List<Retrofit_Model>> GetTypeData();

    @GET("getCountry.php")
    @Headers("Cache-Control: no-cache")
    Call<List<Retrofit_Model>> GetCountry();

    @GET("getState.php")
    @Headers("Cache-Control: no-cache")
    Call<List<Retrofit_Model>> GetState();

    @GET("getDistrict.php")
    @Headers("Cache-Control: no-cache")
    Call<List<Retrofit_Model>> GetDistrict();

    //Add Admin

    @GET("searchUser.PHP")
    @Headers("Cache-Control: no-cache")
    Call<List<UserModel>> GetPhoneNumber();

}
