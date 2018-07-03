package com.roverdream.quiz.ui.splash;

import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.ui.base.BaseViewModel;
import com.roverdream.quiz.ui.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void startSeeding() {

    }

    private void decideNextActivity() {

    }
}
