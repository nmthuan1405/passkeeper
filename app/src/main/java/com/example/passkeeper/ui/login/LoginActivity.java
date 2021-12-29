package com.example.passkeeper.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.retrofit.DataWrapper;
import com.example.passkeeper.databinding.ActivityLoginBinding;
import com.example.passkeeper.ui.MainActivity;

public class LoginActivity extends AppCompatActivity implements Observer<DataWrapper<AuthResponse>> {
    private String TAG = "@@LoginAct";
    private ActivityLoginBinding binding;
    private AccountViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(AccountViewModel.class);
        model.getLoginStatus().observe(this, this);

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

    @Override
    public void onChanged(DataWrapper<AuthResponse> data) {
        switch (data.getStatus()) {
            case WAITING:
                Log.i(TAG, "Waiting login");
                break;

            case SUCCESS:
                Log.i(TAG, "Login successful");
                SessionManager.getInstance().setToken(data.getData());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case ERROR:
                Log.e(TAG, "Login failed");
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
