package com.roverdream.quiz.ui.splash;

import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.ui.base.BaseViewModel;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void startSeeding() {
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseQuestions()
                .flatMap(aBoolean -> getDataManager().seedDatabaseOptions())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    decideNextActivity();
                }, throwable -> {
                    decideNextActivity();
                }));
    }

    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getNavigator().openLoginActivity();
        } else {
            getNavigator().openMainActivity();
        }
    }
}
