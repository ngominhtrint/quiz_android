package com.roverdream.quiz.ui.login;

public interface LoginNavigator {

    void handleError(Throwable throwable);

    void login();

    void openMainActivity();
}
