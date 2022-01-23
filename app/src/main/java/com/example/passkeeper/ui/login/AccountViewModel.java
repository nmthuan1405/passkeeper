package com.example.passkeeper.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.model.MessageResponse;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.data.repository.AccountRepository;

public class AccountViewModel extends ViewModel {
    private final AccountRepository accountRepository;
    private final MutableLiveData<String> email;
    private final MutableLiveData<String> verifyCode;
    private final MutableLiveData<String> password;

    private LiveData<Resource<AuthResponse>> loginStatus = null;
    private LiveData<Resource<MessageResponse>> emailStatus = null;
    private LiveData<Resource<MessageResponse>> codeStatus = null;
    private LiveData<Resource<MessageResponse>> registerStatus = null;

    public AccountViewModel() {
        accountRepository = new AccountRepository();

        email = new MutableLiveData<>();
        verifyCode = new MutableLiveData<>();
        password = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        accountRepository.login(email, password);
    }

    public void checkEmail(String email) {
        accountRepository.checkEmail(email);
    }

    public void checkVerifyCode(String email, String code) {
        accountRepository.checkVerifyCode(email, code);
    }

    public void register(String email, String password, String code) {
        accountRepository.register(email, password, code);
    }

    public LiveData<Resource<AuthResponse>> getLoginStatus() {
        if (loginStatus == null) {
            loginStatus = accountRepository.getLoginStatus();
        }
        return loginStatus;
    }

    public LiveData<Resource<MessageResponse>> getEmailStatus() {
        if (emailStatus == null) {
            emailStatus = accountRepository.getEmailStatus();
        }
        return emailStatus;
    }

    public LiveData<Resource<MessageResponse>> getCodeStatus() {
        if (codeStatus == null) {
            codeStatus = accountRepository.getVerifyCodeStatus();
        }
        return codeStatus;
    }

    public LiveData<Resource<MessageResponse>> getRegisterStatus() {
        if (registerStatus == null) {
            registerStatus = accountRepository.getRegisterStatus();
        }
        return registerStatus;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public LiveData<String> getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode.setValue(verifyCode);
    }

    public LiveData<String> getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }
}