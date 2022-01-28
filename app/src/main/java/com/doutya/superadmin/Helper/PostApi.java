package com.doutya.superadmin.Helper;


import com.doutya.superadmin.Models.AdminNotifyModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostApi {

    @FormUrlEncoded
    @POST("notificationRequest.php")
    Call<AdminNotifyModel> NotifyAdmin(@Field("Notification_UserToken") String Notification_UserToken);


}
