package com.roverdream.quiz.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.roverdream.quiz.data.local.db.DbHelper;
import com.roverdream.quiz.data.local.prefs.PreferencesHelper;
import com.roverdream.quiz.data.model.api.BlogResponse;
import com.roverdream.quiz.data.model.api.LoginRequest;
import com.roverdream.quiz.data.model.api.LoginResponse;
import com.roverdream.quiz.data.model.api.LogoutResponse;
import com.roverdream.quiz.data.model.api.OpenSourceResponse;
import com.roverdream.quiz.data.model.db.Option;
import com.roverdream.quiz.data.model.db.Question;
import com.roverdream.quiz.data.model.db.User;
import com.roverdream.quiz.data.model.others.QuestionCardData;
import com.roverdream.quiz.data.remote.ApiHeader;
import com.roverdream.quiz.data.remote.ApiHelper;
import com.roverdream.quiz.utils.AppConstants;
import com.roverdream.quiz.utils.CommonUtils;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    private final Gson mGson;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    /**
     * Implemented methods of PreferencesHelper
     * */
    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    /**
     * Implemented methods of DataManager
     * */
    @Override
    public Observable<List<QuestionCardData>> getQuestionCardData() {
        return mDbHelper.getAllQuestions()
                .flatMap(questions -> Observable.fromIterable(questions))
                .flatMap(question -> Observable.zip(
                        mDbHelper.getOptionsForQuestionId(question.id),
                        Observable.just(question),
                        (option, question1) -> new QuestionCardData(question1, option)))
                .toList()
                .toObservable();
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null);
    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {
        return mDbHelper.isQuestionEmpty()
                .concatMap(isEmpty -> {
                    if (isEmpty) {
                        Type type = $Gson$Types.newParameterizedTypeWithOwner(null, List.class, Question.class);
                        List<Question> questionList = mGson
                                .fromJson(CommonUtils.loadJSONFromAsset(mContext, AppConstants.SEED_DATABASE_QUESTIONS), type);
                        return saveQuestionList(questionList);
                    }
                    return Observable.just(false);
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseOptions() {
        return mDbHelper.isOptionsEmpty()
                .concatMap(isEmpty -> {
                    if (isEmpty) {
                        Type type = new TypeToken<List<Option>>(){}.getType();
                        List<Option> optionList = mGson.fromJson(CommonUtils.loadJSONFromAsset(mContext, AppConstants.SEED_DATABASE_OPTIONS), type);
                        return saveOptionList(optionList);
                    }
                    return Observable.just(false);
                });
    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, LoggedInMode loggedInMode, String userName, String email, String profilePicPath) {
        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserEmail(email);
        setCurrentUserProfilePicUrl(profilePicPath);

        updateApiHeader(userId, accessToken);
    }

    /**
     * Implemented methods of ApiHelper
     * */
    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return mApiHelper.doGoogleLoginApiCall(request);
    }

    @Override
    public Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return mApiHelper.doFacebookLoginApiCall(request);
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return mApiHelper.doServerLoginApiCall(request);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return mApiHelper.doLogoutApiCall();
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return mApiHelper.getBlogApiCall();
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return mApiHelper.getOpenSourceApiCall();
    }

    /**
     * Implemented methods of DbHelper
     * */
    @Override
    public Observable<List<Question>> getAllQuestions() {
        return mDbHelper.getAllQuestions();
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public Observable<List<Option>> getOptionsForQuestionId(Long questionId) {
        return mDbHelper.getOptionsForQuestionId(questionId);
    }

    @Override
    public Observable<Boolean> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<Boolean> isOptionsEmpty() {
        return mDbHelper.isOptionsEmpty();
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return mDbHelper.isQuestionEmpty();
    }

    @Override
    public Observable<Boolean> saveOption(Option option) {
        return mDbHelper.saveOption(option);
    }

    @Override
    public Observable<Boolean> saveOptionList(List<Option> optionList) {
        return mDbHelper.saveOptionList(optionList);
    }

    @Override
    public Observable<Boolean> saveQuestion(Question question) {
        return mDbHelper.saveQuestion(question);
    }

    @Override
    public Observable<Boolean> saveQuestionList(List<Question> questionList) {
        return mDbHelper.saveQuestionList(questionList);
    }
}
