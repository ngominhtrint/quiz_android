package com.roverdream.quiz.ui.about;

import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class AboutFragmentModule {

    @Provides
    AboutViewModel provideAboutViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new AboutViewModel(dataManager, schedulerProvider);
    }
}
