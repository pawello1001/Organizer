package com.example.pawe.organizer.dagger.module;

import android.app.Application;
import android.content.Context;

import com.example.pawe.organizer.base.MyApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private MyApplication mApplication;

    public ApplicationModule(MyApplication myApplication) {
        mApplication = myApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    Context provideContext() {
        return mApplication.getApplicationContext();
    }

}
