package com.doutya.superadmin.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Retrofit_Model {

    String type;

    public Retrofit_Model( String type) {
        this.type = type;
    }

    @SerializedName("type")
    public String getType() {
        return type;
    }
}
