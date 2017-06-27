package com.example.pawe.organizer.base;

import android.app.Application;

import com.example.pawe.organizer.flow.activities.MainActivity;
import com.example.pawe.organizer.dagger.ApplicationComponent;
import com.example.pawe.organizer.dagger.DaggerApplicationComponent;
import com.example.pawe.organizer.dagger.module.ApplicationModule;


public class MyApplication extends Application {
    private static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    public static void inject(MainActivity mainActivity) {
        mApplicationComponent.inject(mainActivity);
    }
}
