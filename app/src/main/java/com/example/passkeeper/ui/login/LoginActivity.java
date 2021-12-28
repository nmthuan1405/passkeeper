package com.example.passkeeper.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.data.RetrofitService;
import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.AuthenticationApi;
import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.model.DataWrapper;
import com.example.passkeeper.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private String TAG = "@@LoginAct";
    private ActivityLoginBinding binding;
    private SessionManager sessionManager;
    private AccountViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = SessionManager.getInstance();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(AccountViewModel.class);
        model.getLoginStatus().observe(this, data -> {
            switch (data.getStatus()) {
                case SUCCESS:
                    finish();
                    break;
                case ERROR:
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        binding.signUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        binding.login.setOnClickListener(view -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();

            model.login(email, password);
        });
    }
}
