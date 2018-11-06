package com.example.anupama.viewmodelarchitecture.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    // public static final String BASE_URL = "http://18.217.219.42";
    // public static final String BASE_URL = "http://18.221.132.218";
    public static final String BASE_URL = "http://13.58.36.34";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }




    private static OkHttpClient getClient(){

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5,TimeUnit.MINUTES)
                .writeTimeout(5,TimeUnit.MINUTES)
                .build();

        return okHttpClient;
    }

}
