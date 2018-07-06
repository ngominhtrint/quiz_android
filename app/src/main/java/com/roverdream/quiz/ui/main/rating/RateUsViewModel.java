package com.roverdream.quiz.ui.main.rating;

import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.ui.base.BaseViewModel;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

public class RateUsViewModel extends BaseViewModel<RateUsCallback> {

    public RateUsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onLaterClick() {
        getNavigator().dismissDialog();
    }

    public void onSubmitClick() {
        getNavigator().dismissDialog();
    }
}
