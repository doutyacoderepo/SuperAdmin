package com.doutya.superadmin.Retrofit;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostApi {
            //Add Community

    @FormUrlEncoded
    @POST("createCommunity.php")
    Call<PostModel> uploadCommunityData(@Field("Ward") String Ward,
                                        @Field("Type") String Type,
                                        @Field("Muncipality") String Muncipality,
                                        @Field("Country") String Country,
                                        @Field("State") String State,
                                        @Field("District") String District,
                                        @Field("AddedOn") String AddedOn
                                      //@Field("AddedBy") String AddedBy,
                                        );
            //Add Admin
    @FormUrlEncoded
    @POST("adminPendingApproval.php")
    Call<PostModel> uploadAdminPendingApproval(@Field("ContactNumber") String ContactNumber,
                                        @Field("CommunityID") String CommunityID,
                                       // @Field("InvitedBy") String InvitedBy,
                                        @Field("InvitedDate") String InvitedDate);



}
