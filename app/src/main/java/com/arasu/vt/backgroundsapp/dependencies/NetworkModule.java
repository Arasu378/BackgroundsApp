package com.arasu.vt.backgroundsapp.dependencies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kyros on 02-11-2017.
 */
@Module
public class NetworkModule {
    private String mBaseUrl;

    public NetworkModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }
    @Provides
    @Singleton
    RxJavaCallAdapterFactory providesRxJavaCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }
    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }
    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory,RxJavaCallAdapterFactory rxJavaCallAdapterFactory){
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }
}
