package com.doutya.superadmin.Models;

import com.google.gson.annotations.SerializedName;

public class AdminNotifyModel {

    @SerializedName("Notification_UserToken")
    private String FcmToken;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
