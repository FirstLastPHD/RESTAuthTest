package com.zps.tajmac.auth;


import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.MultipartBody;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiAuthenticationClient {


    private String username;
    private String password;
    static byte[] key = null ;
    private CompositeDisposable mCompositeDisposable;

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }


    public ApiAuthenticationClient( String username, String password) {

        this.username = username;
        this.password = password;

    }

    /**
     * Make the call to the Rest API and return its response as a string.
     * @return String
     */
    public byte[] execute() {



        try{
            HashMap<String, Object> postData33 = new HashMap<>();
            postData33.put("userName",username);
            postData33.put("password",password);
            Call<ResponseBody> call = QRCodeRESTAPIService
                    .getInstance()
                    .getApi()
                    .getAuthUser(postData33);

            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                    try {
                        if(response.body()!=null ) {
                            key = response.body().bytes();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("Not Success...", "onFailure: ");
                }

            });}


        catch(Exception e){}

      return key;
    }
}




