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

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.MessageResponse;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.VerifyCodeFragmentBinding;
import com.example.passkeeper.ui.login.AccountViewModel;
import com.example.passkeeper.ui.utils.EventObserver;

public class VerifyCode extends Fragment implements View.OnClickListener {

    private AccountViewModel mViewModel;
    private NavController navController;
    private VerifyCodeFragmentBinding binding;

    public static VerifyCode newInstance() {
        return new VerifyCode();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = VerifyCodeFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);

        binding.nextBtn.setOnClickListener(this);
        mViewModel.getCodeStatus().observe(getViewLifecycleOwner(), new EventObserver<MessageResponse>(getActivity()) {
            @Override
            public void onHandle(Resource<MessageResponse> data) {
                navController.navigate(R.id.action_verifyCode_to_setPassword);
            }
        });
    }

    @Override
    public void onClick(View view) {
        String email = mViewModel.getEmail();
        String code = binding.verifyCodeInput.getText().toString();

        mViewModel.setVerifyCode(code);
        mViewModel.checkVerifyCode(email, code);
    }
}