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
import com.example.passkeeper.databinding.ViewCardFragmentBinding;
import com.example.passkeeper.ui.record.RecordViewModel;
import com.example.passkeeper.ui.utils.ActivityObserver;

public class ViewCardFragment extends Fragment {
    private RecordViewModel viewModel;
    private ViewCardFragmentBinding binding;

    public static ViewCardFragment newInstance() {
        return new ViewCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ViewCardFragmentBinding.inflate(inflater, container, false);
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
                binding.cardholderName.setText(record.getFieldValue("cardholderName"));
                binding.cardNumber.setText(record.getFieldValue("cardNumber"));
                binding.expirationDay.setText(record.getFieldValue("expirationDay"));
                binding.note.setText(record.getFieldValue("note"));

            }
        });
    }
}
