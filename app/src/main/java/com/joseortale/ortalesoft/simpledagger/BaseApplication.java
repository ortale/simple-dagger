package com.joseortale.ortalesoft.simpledagger;

import android.app.Application;

import com.joseortale.ortalesoft.simpledagger.di.components.AppComponent;
import com.joseortale.ortalesoft.simpledagger.di.components.DaggerAppComponent;

public class BaseApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
