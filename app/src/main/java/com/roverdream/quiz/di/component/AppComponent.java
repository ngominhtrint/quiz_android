package com.roverdream.quiz.di.component;

import android.app.Application;

import com.roverdream.quiz.App;
import com.roverdream.quiz.di.builder.ActivityBuilder;
import com.roverdream.quiz.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
