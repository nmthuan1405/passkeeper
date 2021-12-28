package com.example.passkeeper.data;

import android.util.Log;

import com.example.passkeeper.data.model.AuthResponse;

public class SessionManager {
    private final String TAG = "@@SessionMgr";
    private final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final String ACCESS_TOKEN = "ACCESS_TOKEN";

    private static SessionManager instance = null;
    private OnLoginListener onLoginListener = null;
    private AuthResponse auth = null;

    public interface OnLoginListener {
        void onLogin();
    }

    private SessionManager() {
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

        if (refresh == null || access == null) {
            if (onLoginListener != null) {
                onLoginListener.onLogin();
            }
        }
        else {
            auth = new AuthResponse(refresh, access);
        }
    }

    public void setToken(AuthResponse authResponse) {
        if (authResponse != null)
        {
            SharedPref.write(REFRESH_TOKEN, authResponse.getRefresh());
            SharedPref.write(ACCESS_TOKEN, authResponse.getAccess());
        }
        else {
            SharedPref.write(REFRESH_TOKEN, null);
            SharedPref.write(ACCESS_TOKEN, null);
        }

        auth = authResponse;
    }

    public String getAccessToken() {
        if (auth != null) {
            return "Bearer " + auth.getAccess();
        }

        return null;
    }

    public void setOnLoginListener(OnLoginListener listener) {
        onLoginListener = listener;
    }
}
