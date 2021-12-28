package com.example.passkeeper.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.RetrofitService;
import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.AuthenticationApi;
import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.model.DataWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private static AccountRepository accountRepository = null;

    private final String TAG = "@@AC_Repo";
    private final SessionManager sessionManager;
    private final MutableLiveData<DataWrapper<Boolean>> loginStatus;

    private AccountRepository() {
        sessionManager = SessionManager.getInstance();
        loginStatus = new MutableLiveData<>();
    }

    public static AccountRepository getInstance() {
        if (accountRepository == null) {
            accountRepository = new AccountRepository();
        }
        return accountRepository;
    }

    public void login(String email, String password) {
        Log.i(TAG, "Starting login");
        loginStatus.setValue(DataWrapper.WAITING());
        AuthenticationApi authenticationApi = RetrofitService.createService(AuthenticationApi.class);
        authenticationApi.login(new AuthRequest(email, password)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Login successful");
                    sessionManager.setToken(response.body());
                    loginStatus.setValue(DataWrapper.SUCCESS(true));
                }
                else {
                    Log.i(TAG, "Login code: " + response.code());
                    loginStatus.setValue(DataWrapper.ERROR());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.i(TAG, "Login failed: " + t.getMessage());
                loginStatus.setValue(DataWrapper.ERROR());
            }
        });
    }

    public LiveData<DataWrapper<Boolean>> getLoginStatus() {
        if (loginStatus == null) {
            Log.i(TAG, "Error Null pointer");
        }
        return loginStatus;
    }
}
