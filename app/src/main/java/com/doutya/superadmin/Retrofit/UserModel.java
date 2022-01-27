package com.doutya.superadmin.Retrofit;

import com.google.gson.annotations.SerializedName;

public class UserModel {

            //Add Admin Search User
    String phone;

    public UserModel(String phone) {
        this.phone = phone;
    }

    @SerializedName("phone")
    public String getPhone() {
        return phone;
    }
}
