package com.roverdream.quiz.ui.main.rating;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RateUsDialogProvider {

    @ContributesAndroidInjector(modules = RateUsDialogModule.class)
    abstract RateUsDialog provideRateUsDialogFactory();
}
