package com.yomi.sweat;

import android.app.Application;

import com.yomi.sweat.BuildConfig;

import timber.log.Timber;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
    }

    private void initTimber(){
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
