package com.roverdream.quiz.data;

import android.content.Context;

import com.roverdream.quiz.data.local.db.DbHelper;
import com.roverdream.quiz.data.local.prefs.PreferencesHelper;
import com.roverdream.quiz.data.model.api.BlogResponse;
import com.roverdream.quiz.data.model.api.LoginRequest;
import com.roverdream.quiz.data.model.api.LoginResponse;
import com.roverdream.quiz.data.model.api.LogoutResponse;
import com.roverdream.quiz.data.model.api.OpenSourceResponse;
import com.roverdream.quiz.data.remote.ApiHeader;
import com.roverdream.quiz.data.remote.ApiHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

//    private final Context mContext;
//    private final DbHelper mDbHelper;
//    private final PreferencesHelper mPreferencesHelper;
//    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager() {

    }

    @Override
    public String getAccessToken() {
        return null;
    }

    @Override
    public void setAccessToken(String accessToken) {

    }

    @Override
    public String getCurrentUserEmail() {
        return null;
    }

    @Override
    public void setCurrentUserEmail(String email) {

    }

    @Override
    public Long getCurrentUserId() {
        return null;
    }

    @Override
    public void setCurrentUserId(Long userId) {

    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return 0;
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {

    }

    @Override
    public String getCurrentUserName() {
        return null;
    }

    @Override
    public void setCurrentUserName(String userName) {

    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return null;
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {

    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {

    }

    @Override
    public void setUserAsLoggedOut() {

    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {
        return null;
    }

    @Override
    public Observable<Boolean> seedDatabaseOptions() {
        return null;
    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, LoggedInMode loggedInMode, String userName, String email, String profilePicPath) {

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
