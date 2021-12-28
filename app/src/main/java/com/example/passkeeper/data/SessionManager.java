package com.example.passkeeper.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.passkeeper.data.model.AuthResponse;

public class SessionManager {
    private final String TAG = "@@SessionMgr";
    private final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final String ACCESS_TOKEN = "ACCESS_TOKEN";

    private static SessionManager instance = null;
    private AuthResponse auth = null;
    private MutableLiveData<Boolean> isLogin;
    private OnLoginListener onLoginListener = null;

    public interface OnLoginListener {
        void onLogin();
    }

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }

    private SessionManager() {
        isLogin = new MutableLiveData<>();
        isLogin.observeForever(isLogin -> {
            if (!isLogin && onLoginListener != null) {
                onLoginListener.onLogin();
            }
        });
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void fetchToken() {
        Log.i(TAG, "Fetch token");
        String refresh = SharedPref.read(REFRESH_TOKEN, null);
        String access = SharedPref.read(ACCESS_TOKEN, null);

        if (refresh != null && access != null) {
            auth = new AuthResponse(refresh, access);
            isLogin.setValue(true);
        }
        else {
            Log.i(TAG, "Login status is false");
            isLogin.setValue(false);
        }
    }

    public void setToken(AuthResponse authResponse) {
        auth = authResponse;
        if (authResponse != null) {
            Log.i(TAG, "Set valid token");
            SharedPref.write(REFRESH_TOKEN, authResponse.getRefresh());
            SharedPref.write(ACCESS_TOKEN, authResponse.getAccess());
            isLogin.setValue(true);
        }
        else {
            Log.i(TAG, "Set null token");
            SharedPref.write(REFRESH_TOKEN, null);
            SharedPref.write(ACCESS_TOKEN, null);
        }
    }

    public String getAccessToken() {
        if (auth != null) {
            return "Bearer " + auth.getAccess();
        }
        return null;
    }

    public void logOut() {
        isLogin.setValue(false);
    }
}