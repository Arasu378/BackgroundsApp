package com.arasu.vt.backgroundsapp.application;

import com.arasu.vt.backgroundsapp.interfaces.POJOInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kyros on 31-10-2017.
 */

public class ApiClient {
    public static final String BASE_URL="https://api.unsplash.com";
    private static Retrofit retrofit=null;
    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                 //   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public POJOInterface getPOJOInterface(){
        final Retrofit retrofit=getRetrofit();
        return retrofit.create(POJOInterface.class);
    }
}
