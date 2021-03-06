package com.roverdream.quiz.di.builder;

import com.roverdream.quiz.ui.about.AboutFragmentProvider;
import com.roverdream.quiz.ui.feed.FeedActivity;
import com.roverdream.quiz.ui.feed.FeedActivityModule;
import com.roverdream.quiz.ui.feed.blogs.BlogFragmentModule;
import com.roverdream.quiz.ui.feed.blogs.BlogFragmentProvider;
import com.roverdream.quiz.ui.feed.opensource.OpenSourceFragment;
import com.roverdream.quiz.ui.feed.opensource.OpenSourceFragmentModule;
import com.roverdream.quiz.ui.feed.opensource.OpenSourceFragmentProvider;
import com.roverdream.quiz.ui.login.LoginActivity;
import com.roverdream.quiz.ui.login.LoginActivityModule;
import com.roverdream.quiz.ui.main.MainActivity;
import com.roverdream.quiz.ui.main.MainActivityModule;
import com.roverdream.quiz.ui.main.rating.RateUsDialogProvider;
import com.roverdream.quiz.ui.splash.SplashActivity;
import com.roverdream.quiz.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            AboutFragmentProvider.class,
            RateUsDialogProvider.class })
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {
            FeedActivityModule.class,
            BlogFragmentProvider.class,
            OpenSourceFragmentProvider.class
    })
    abstract FeedActivity bindFeedActivity();
}
