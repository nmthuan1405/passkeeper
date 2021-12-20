package com.example.passkeeper.ui.login.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.passkeeper.R;
import com.example.passkeeper.ui.login.SignUpViewModel;

public class VerifyCode extends Fragment implements View.OnClickListener {

    private SignUpViewModel mViewModel;
    private Button nextBtn;

    public static VerifyCode newInstance() {
        return new VerifyCode();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.verify_code_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);

        nextBtn = view.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_verifyCode_to_setPassword);
    }
}