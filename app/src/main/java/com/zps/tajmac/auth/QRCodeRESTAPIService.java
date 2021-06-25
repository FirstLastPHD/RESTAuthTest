package com.zps.tajmac.auth;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class QRCodeRESTAPIService {

    private static final String BASE_URL = "http://172.20.9.49:9595/";
    private static QRCodeRESTAPIService mInstance;
    private Retrofit retrofit;


    private QRCodeRESTAPIService(){

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //client.connectTimeout(35, TimeUnit.SECONDS);
        //client.readTimeout(35, TimeUnit.SECONDS);
        //client.writeTimeout(35, TimeUnit.SECONDS);


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }

    }

    public static synchronized  QRCodeRESTAPIService getInstance(){

        if(mInstance == null){
            mInstance = new QRCodeRESTAPIService();
        }

        return mInstance;
    }

    public Api getApi(){

        return retrofit.create(Api.class);
    }

}

