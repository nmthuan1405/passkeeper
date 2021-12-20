package com.example.passkeeper.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passkeeper.R;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    TextView signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initInterface();

        signUpBtn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void initInterface() {
        loginBtn = findViewById(R.id.login);
        signUpBtn = findViewById(R.id.sign_up);
    }
}
