package com.roverdream.quiz.ui.feed;

import android.arch.lifecycle.ViewModelProvider;

import com.roverdream.quiz.ViewModelProviderFactory;
import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.ui.main.MainViewModel;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class FeedActivityModule {

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(FeedActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    FeedViewModel provideFeedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new FeedViewModel(dataManager, schedulerProvider);
    }
}
