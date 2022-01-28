package com.doutya.superadmin.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Retrofit_Model {

    String type;
    String country;
    String state;
    String district;



    @SerializedName("type")
    public String getType() {
        return type;
    }

    @SerializedName("country")
    public String getCountry() {
        return country;
    }

    @SerializedName("state")
    public String getState() {
        return state;
    }

    @SerializedName("district")
    public String getDistrict() {
        return district;
    }
}
