package com.arasu.vt.backgroundsapp.dependencies;

import com.arasu.vt.backgroundsapp.activity.MainActivity;
import com.arasu.vt.backgroundsapp.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by kyros on 02-11-2017.
 */
@CustomScope
@Component(modules = ApiModule.class,dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(HomeFragment fragment);
}
