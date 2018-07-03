package com.roverdream.quiz.data.remote;

import com.roverdream.quiz.data.model.api.BlogResponse;
import com.roverdream.quiz.data.model.api.LoginRequest;
import com.roverdream.quiz.data.model.api.LoginResponse;
import com.roverdream.quiz.data.model.api.LogoutResponse;
import com.roverdream.quiz.data.model.api.OpenSourceResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return null;
    }

    @Override
    public Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return null;
    }

    @Override
    public Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return null;
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return null;
    }

    @Override
    public Observable<LogoutResponse> doLogoutApiCall() {
        return null;
    }

    @Override
    public Observable<BlogResponse> getBlogApiCall() {
        return null;
    }

    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return null;
    }
}
