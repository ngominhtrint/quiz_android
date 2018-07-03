package com.roverdream.quiz.di.module;

import com.roverdream.quiz.data.remote.ApiHelper;
import com.roverdream.quiz.data.remote.AppApiHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }
}
