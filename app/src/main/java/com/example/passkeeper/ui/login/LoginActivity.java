package com.example.passkeeper.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passkeeper.data.RetrofitService;
import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.AuthenticationApi;
import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private String TAG = "@@LoginAct";
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        binding.login.setOnClickListener(view -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            login(email, password);
        });
    }

    private void login(String email, String password) {
        Log.i(TAG, "Starting login");
        AuthenticationApi authenticationApi = RetrofitService.createService(AuthenticationApi.class);
        authenticationApi.login(new AuthRequest(email, password)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Log.i(TAG, "Login code: " + response.code());
                if (response.isSuccessful()) {
                    Log.i(TAG, "Login successful");
                    SessionManager.getInstance().setToken(response.body());
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.i(TAG, "Login failed: " + t.getMessage());
                SessionManager.getInstance().setToken(null);
            }
        });
    }
}
