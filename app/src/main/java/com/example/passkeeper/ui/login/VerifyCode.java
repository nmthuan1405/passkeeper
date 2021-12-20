package com.example.passkeeper.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.passkeeper.R;

public class VerifyCode extends Fragment implements View.OnClickListener {

    private VerifyCodeViewModel mViewModel;
    private Button nextBtn;

    public static VerifyCode newInstance() {
        return new VerifyCode();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_code_fragment, container, false);
        nextBtn = view.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VerifyCodeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_verifyCode_to_setPassword);
    }
}