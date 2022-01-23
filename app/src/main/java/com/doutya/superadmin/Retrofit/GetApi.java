package com.doutya.superadmin.Retrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetApi {

    @GET("getCommunityType.php")
    @Headers("Cache-Control: no-cache")
        Call<List<Retrofit_Model>> GetTypeData();
}
