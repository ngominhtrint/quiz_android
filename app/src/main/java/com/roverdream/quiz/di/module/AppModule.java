package com.roverdream.quiz.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.roverdream.quiz.BuildConfig;
import com.roverdream.quiz.R;
import com.roverdream.quiz.data.AppDataManager;
import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.data.local.db.AppDatabase;
import com.roverdream.quiz.data.local.db.AppDbHelper;
import com.roverdream.quiz.data.local.db.DbHelper;
import com.roverdream.quiz.data.local.prefs.AppPrefencesHelper;
import com.roverdream.quiz.data.local.prefs.PreferencesHelper;
import com.roverdream.quiz.data.remote.ApiHeader;
import com.roverdream.quiz.data.remote.ApiHelper;
import com.roverdream.quiz.data.remote.AppApiHelper;
import com.roverdream.quiz.di.ApiInfo;
import com.roverdream.quiz.di.DatabaseInfo;
import com.roverdream.quiz.di.PreferenceInfo;
import com.roverdream.quiz.utils.AppConstants;
import com.roverdream.quiz.utils.rx.AppSchedulerProvider;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.tff")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPrefencesHelper appPrefencesHelper) {
        return appPrefencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey, PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
