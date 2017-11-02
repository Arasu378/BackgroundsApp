package com.arasu.vt.backgroundsapp.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by kyros on 02-11-2017.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
Retrofit retrofit();
}
