package com.roverdream.quiz.data;

import com.roverdream.quiz.data.local.db.DbHelper;
import com.roverdream.quiz.data.local.prefs.PreferencesHelper;
import com.roverdream.quiz.data.model.others.QuestionCardData;
import com.roverdream.quiz.data.remote.ApiHelper;

import java.util.List;

import io.reactivex.Observable;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    Observable<List<QuestionCardData>> getQuestionCardData();

    Observable<Boolean> seedDatabaseQuestions();

    Observable<Boolean> seedDatabaseOptions();

    void setUserAsLoggedOut();

    void updateApiHeader(Long userId, String accessToken);

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath
    );

    public enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
