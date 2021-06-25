package com.zps.tajmac.auth;



import java.util.HashMap;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("/authenticate")
    Call<ResponseBody> getAuthUser(@Body HashMap<String, Object> json);
}
