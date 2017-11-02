package com.arasu.vt.backgroundsapp.application;

import android.app.Application;

import com.arasu.vt.backgroundsapp.dependencies.ApiComponent;
import com.arasu.vt.backgroundsapp.dependencies.DaggerApiComponent;
import com.arasu.vt.backgroundsapp.dependencies.DaggerNetworkComponent;
import com.arasu.vt.backgroundsapp.dependencies.NetworkComponent;
import com.arasu.vt.backgroundsapp.dependencies.NetworkModule;
import com.arasu.vt.backgroundsapp.utils.Utils;

/**
 * Created by kyros on 02-11-2017.
 */

public class PhotoApplication extends Application {
    private ApiComponent mApiComponent;
    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent= DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }
    public NetworkComponent getNetworkComponent(){
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Utils.BASE_URL))
                .build();
    }
public ApiComponent getApiComponent(){
        return mApiComponent;
}

}
