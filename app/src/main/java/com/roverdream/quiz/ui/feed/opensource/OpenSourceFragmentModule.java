package com.roverdream.quiz.ui.feed.opensource;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.roverdream.quiz.ViewModelProviderFactory;
import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class OpenSourceFragmentModule {

    @Provides
    OpenSourceViewModel openSourceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new OpenSourceViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(OpenSourceFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter();
    }

    @Provides
    ViewModelProvider.Factory provideOpenSourceViewModel(OpenSourceViewModel openSourceViewModel) {
        return new ViewModelProviderFactory<>(openSourceViewModel);
    }
}
