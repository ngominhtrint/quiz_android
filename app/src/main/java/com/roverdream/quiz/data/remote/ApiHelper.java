package com.roverdream.quiz.data.remote;

import com.roverdream.quiz.data.model.api.BlogResponse;
import com.roverdream.quiz.data.model.api.LoginRequest;
import com.roverdream.quiz.data.model.api.LoginResponse;
import com.roverdream.quiz.data.model.api.LogoutResponse;
import com.roverdream.quiz.data.model.api.OpenSourceResponse;

import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();
}
