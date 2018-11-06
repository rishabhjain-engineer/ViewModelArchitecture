package com.example.anupama.viewmodelarchitecture.Network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRequest {

    private static ApiRequest INSTANCE;
    private NetworkResponse listener ;

    public static ApiRequest getApiRequestInstance(){

        if(INSTANCE == null){

            synchronized (ApiRequest.class){

                if(INSTANCE == null) {

                    INSTANCE = new ApiRequest();
                }

            }

        }

        return INSTANCE ;
    }


    public void hitAPIRequest(Context context , @NonNull Call<JsonElement> call, final String tag){

        listener = (NetworkResponse) context;

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                String d = response.body().toString() ;
                try {

                    JSONObject jsonObject = new JSONObject(d);
                    if(jsonObject.optBoolean("status")){
                        listener.onNetworkSuccess(jsonObject,tag);
                    }else {
                        listener.onNetworkError("Something went wrong");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onNetworkError(e.toString());
                }


            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                listener.onNetworkError(t.getLocalizedMessage());
            }
        });


    }


    public interface NetworkResponse{

        // Tag: refers to the method name, who called the hitAPIRequest.
        void onNetworkSuccess(JSONObject jsonObject, String tag);
        void onNetworkError(String error);

    }



}
