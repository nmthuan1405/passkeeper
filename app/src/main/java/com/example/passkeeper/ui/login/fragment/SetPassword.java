package com.example.passkeeper.ui.login.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passkeeper.data.model.MessageResponse;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.SetPasswordFragmentBinding;
import com.example.passkeeper.ui.login.AccountViewModel;
import com.example.passkeeper.ui.utils.EventObserver;

public class SetPassword extends Fragment implements View.OnClickListener {

    private AccountViewModel mViewModel;
    private NavController navController;
    private SetPasswordFragmentBinding binding;

    public static SetPassword newInstance() {
        return new SetPassword();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SetPasswordFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);

        binding.savePassBtn.setOnClickListener(this);
        mViewModel.getRegisterStatus().observe(getViewLifecycleOwner(), new EventObserver<MessageResponse>(getActivity()) {
            @Override
            public void onHandle(Resource<MessageResponse> data) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        String password = binding.newPasswordInput.getText().toString();
        String reEnterPassword = binding.reEnterPasswordInput.getText().toString();
        String email = mViewModel.getEmail();
        String code = mViewModel.getVerifyCode();

        if (password.equals(reEnterPassword)) {
            mViewModel.setPassword(password);
            mViewModel.register(email, password, code);
        }
    }
}