package com.roverdream.quiz.ui.main.rating;

import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class RateUsDialogModule {

    @Provides
    RateUsViewModel provideRateUsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new RateUsViewModel(dataManager, schedulerProvider);
    }
}
