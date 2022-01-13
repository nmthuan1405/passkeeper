package com.example.passkeeper.ui.record.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ViewPasswordFragmentBinding;
import com.example.passkeeper.ui.record.RecordViewModel;
import com.example.passkeeper.ui.utils.ActivityObserver;

public class ViewPasswordFragment extends Fragment {
    private RecordViewModel viewModel;
    private ViewPasswordFragmentBinding binding;

    public static ViewPasswordFragment newInstance() {
        return new ViewPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ViewPasswordFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(RecordViewModel.class);
        viewModel.getRecord().observe(getViewLifecycleOwner(), new ActivityObserver<Record>(getActivity()) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Record record = data.getData();

                binding.name.setText(record.getFieldValue("name"));
                binding.username.setText(record.getFieldValue("username"));
                binding.password.setText(record.getFieldValue("password"));
                binding.urls.setText(record.getFieldValue("urls"));
            }
        });
    }
}
