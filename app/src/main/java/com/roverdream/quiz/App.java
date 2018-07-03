package com.roverdream.quiz;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.roverdream.quiz.di.component.AppComponent;
import com.roverdream.quiz.di.component.DaggerAppComponent;
import com.roverdream.quiz.ui.utils.AppLogger;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

//    @Inject
//    CalligraphyConfig mCalligraphyConfig;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

//        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }
}
