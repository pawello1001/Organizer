package com.example.pawe.organizer.base;

import android.app.Application;

import com.example.pawe.organizer.flow.activities.MainActivity;
import com.example.pawe.organizer.dagger.ApplicationComponent;
import com.example.pawe.organizer.dagger.DaggerApplicationComponent;
import com.example.pawe.organizer.dagger.module.ApplicationModule;
import com.example.pawe.organizer.flow.activities.SingleAlarmActivity;
import com.example.pawe.organizer.flow.activities.SingleNoteActivity;


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

    public static void inject(SingleNoteActivity singleNoteActivity) {
        mApplicationComponent.inject(singleNoteActivity);
    }

    public static void inject(SingleAlarmActivity singleAlarmActivity) {
        mApplicationComponent.inject(singleAlarmActivity);
    }
}
