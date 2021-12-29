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

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public boolean fetchToken() {
        Log.i(TAG, "Fetch token from storage");
        String refresh = SharedPref.read(REFRESH_TOKEN, null);
        String access = SharedPref.read(ACCESS_TOKEN, null);

        if (refresh != null && access != null) {
            auth = new AuthResponse(refresh, access);
            return true;
        }
        return false;
    }

    public void setToken(AuthResponse authResponse) {
        if (authResponse != null) {
            Log.i(TAG, "Set token");
            SharedPref.write(REFRESH_TOKEN, authResponse.getRefresh());
            SharedPref.write(ACCESS_TOKEN, authResponse.getAccess());

            auth = authResponse;
        }
        else if (auth != null) {
            Log.i(TAG, "Set null token");
            SharedPref.write(REFRESH_TOKEN, null);
            SharedPref.write(ACCESS_TOKEN, null);

            auth = null;
        }
    }

    public String getAccessToken() {
        if (auth != null) {
            return "Bearer " + auth.getAccess();
        }
        return null;
    }

    public String getRefreshToken() {
        if (auth != null) {
            return "Bearer " + auth.getRefresh();
        }
        return null;
    }
}