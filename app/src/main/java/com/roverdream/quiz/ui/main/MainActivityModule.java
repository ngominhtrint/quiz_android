package com.roverdream.quiz.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.roverdream.quiz.ViewModelProviderFactory;
import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MainViewModel(dataManager, schedulerProvider);
    }
}
