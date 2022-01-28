package com.doutya.superadmin.Models;

public class SearchUser {
    String Contact_Number,Name,FcmToken;

    public SearchUser() {
    }

    public SearchUser(String contact_Number, String name, String fcmToken) {
        Contact_Number = contact_Number;
        Name = name;
        FcmToken = fcmToken;
    }


    public String getContact_Number() {
        return Contact_Number;
    }

    public void setContact_Number(String contact_Number) {
        Contact_Number = contact_Number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFcmToken() {
        return FcmToken;
    }

    public void setFcmToken(String fcmToken) {
        FcmToken = fcmToken;
    }
}
