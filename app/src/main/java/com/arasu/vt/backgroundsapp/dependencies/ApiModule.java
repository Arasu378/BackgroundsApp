package com.arasu.vt.backgroundsapp.dependencies;

import com.arasu.vt.backgroundsapp.interfaces.POJOInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by kyros on 02-11-2017.
 */
@Module
public class ApiModule {
    @Provides
    @CustomScope
    POJOInterface providePojoService(Retrofit retrofit){
        return retrofit.create(POJOInterface.class);
    }
}
