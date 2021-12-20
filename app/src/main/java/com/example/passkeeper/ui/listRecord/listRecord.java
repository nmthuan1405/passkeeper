package com.example.passkeeper.ui.listRecord;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.databinding.ListRecordFragmentBinding;

public class listRecord extends Fragment {

    private ListRecordViewModel mViewModel;
    private ListRecordFragmentBinding binding;

    public static listRecord newInstance() {
        return new listRecord();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ListRecordFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListRecordViewModel.class);

        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        binding.demoText.setText(String.valueOf(type));
    }
}