package com.doutya.superadmin.Retrofit;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostModel {

    //Upload New Community Data

   /* @Expose
    @SerializedName("added_by")
    private String added_by;*/

    @Expose
    @SerializedName("Ward")
    private String name;

    @Expose
    @SerializedName("Type")
    private String type;

    @Expose
    @SerializedName("Muncipality")
    private String muncipality;

    @Expose
    @SerializedName("Country")
    private String country;

    @Expose
    @SerializedName("State")
    private String state;

    @Expose
    @SerializedName("District")
    private String district;

    @Expose
    @SerializedName("AddedOn")
    private String addedOn;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        Log.e("comm",Response);
        return Response;
    }


    //UploadPendingApproval - Add admin

    @Expose
    @SerializedName("invited_date")
    private String invited_date;

    /*@Expose
    @SerializedName("invited_by")
    private String invited_by;*/

    @Expose
    @SerializedName("CommunityID")
    private String community_id;

    @Expose
    @SerializedName("ContactNumber")
    private String ContactNumber;





}
