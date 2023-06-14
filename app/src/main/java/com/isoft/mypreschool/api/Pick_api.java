package com.isoft.mypreschool.api;

import com.isoft.mypreschool.modelclass.Forgot_model;
import com.isoft.mypreschool.modelclass.LoginAdmin_model;
import com.isoft.mypreschool.modelclass.Login_model;
import com.isoft.mypreschool.modelclass.Save_model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Pick_api {

//    @FormUrlEncoded
//    @POST("upload_fuelbill.php")
//    Call<JsonObject> uploadfuelbill(@Field("did") String driver, @Field("fuelbill") String photos,
//                                    @Field("vin") String vin, @Field("bill_date") String bill_date, @Field("address") String address);


    @POST("login.php")
    Call<Login_model> getQLogin(
            @Query("pin") String pin
    );
    @GET("forgotpwd.php")
    Call<Forgot_model> getForgotpassword(
            @Query("email") String email
    );
    @FormUrlEncoded
    @POST("pickinsert.php")
    Call<Save_model> savedata_pick(@Field("id") String id, @Field("pick") String pick,
                                   @Field("user_type") String user_type, @Field("photo") String photo);
    @POST("school_login.php")
    Call<LoginAdmin_model> getadminLogin(
            @Query("email") String email,@Query("password") String password
    );

}

