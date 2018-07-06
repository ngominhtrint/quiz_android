package com.roverdream.quiz.ui.login;

import android.text.TextUtils;

import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.data.model.api.LoginRequest;
import com.roverdream.quiz.ui.base.BaseViewModel;
import com.roverdream.quiz.utils.CommonUtils;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false;
        }

        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            return false;
        }

        return true;
    }

    public void login(String email, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password))
                .doOnSuccess(loginResponse -> getDataManager()
                    .updateUserInfo(
                            loginResponse.getAccessToken(),
                            loginResponse.getUserId(),
                            DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                            loginResponse.getUserName(),
                            loginResponse.getUserEmail(),
                            loginResponse.getGoogleProfilePicUrl()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(loginResponse -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity();
//                    getNavigator().handleError(throwable);
                }));
    }

    public void onFbLoginClick() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doFacebookLoginApiCall(new LoginRequest.FacebookLoginRequest("test3", "test4"))
                .doOnSuccess(loginResponse -> getDataManager()
                    .updateUserInfo(
                            loginResponse.getAccessToken(),
                            loginResponse.getUserId(),
                            DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                            loginResponse.getUserName(),
                            loginResponse.getUserEmail(),
                            loginResponse.getGoogleProfilePicUrl()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(loginResponse -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void onGoogleLoginClick() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
            .doGoogleLoginApiCall(new LoginRequest.GoogleLoginRequest("test1", "test1"))
            .doOnSuccess(loginResponse -> getDataManager()
                .updateUserInfo(
                        loginResponse.getAccessToken(),
                        loginResponse.getUserId(),
                        DataManager.LoggedInMode.LOGGED_IN_MODE_GOOGLE,
                        loginResponse.getUserName(),
                        loginResponse.getUserEmail(),
                        loginResponse.getGoogleProfilePicUrl()))
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(loginResponse -> {
                setIsLoading(false);
                getNavigator().openMainActivity();
            }, throwable -> {
                setIsLoading(false);
                getNavigator().handleError(throwable);
            }));
    }

    public void onServerLoginClick() {
        getNavigator().login();
    }
}
