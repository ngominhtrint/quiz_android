package com.roverdream.quiz.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.roverdream.quiz.di.ApiInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;

    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(ProtectedApiHeader protectedApiHeader, PublicApiHeader publicApiHeader) {
        this.mProtectedApiHeader = protectedApiHeader;
        this.mPublicApiHeader = publicApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("access_token")
        private String mAccessToken;

        @Expose
        @SerializedName("api_key")
        private String mApiKey;

        @Expose
        @SerializedName("user_id")
        private Long mUserId;

        public ProtectedApiHeader(String apiKey, Long userId, String accessToken) {
            this.mApiKey = apiKey;
            this.mUserId = userId;
            this.mAccessToken = accessToken;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            this.mAccessToken = accessToken;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            this.mApiKey = apiKey;
        }

        public Long getUserId() {
            return mUserId;
        }

        public void setUserId(Long userId) {
            this.mUserId = userId;
        }
    }

    public static final class PublicApiHeader {

        @Expose
        @SerializedName("api_key")
        private String mApiKey;

        @Inject
        public PublicApiHeader(@ApiInfo String apiKey) {
            mApiKey = apiKey;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }
    }
}
