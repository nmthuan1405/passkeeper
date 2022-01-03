package com.example.passkeeper.ui.login.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.passkeeper.R;
import com.example.passkeeper.ui.login.AccountViewModel;

public class SetPassword extends Fragment implements View.OnClickListener {

    private AccountViewModel mViewModel;
    private Button savePassBtn;
    public static SetPassword newInstance() {
        return new SetPassword();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.set_password_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);

        savePassBtn = view.findViewById(R.id.save_pass_btn);
        savePassBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        getActivity().finish();
    }
}