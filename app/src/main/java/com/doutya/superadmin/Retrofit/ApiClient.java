package com.doutya.superadmin.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static final String BaseUrl = "http://192.168.1.3:80/testapp/AdminApp/";

    public static Retrofit getApiClient() {

        Gson gson = new GsonBuilder().setLenient().create();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();

    }
}
