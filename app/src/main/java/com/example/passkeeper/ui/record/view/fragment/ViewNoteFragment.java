package com.example.passkeeper.ui.record.view.fragment;

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
import com.example.passkeeper.databinding.ViewNoteFragmentBinding;
import com.example.passkeeper.ui.record.view.ViewRecordViewModel;
import com.example.passkeeper.ui.utils.ActivityObserver;

public class ViewNoteFragment extends Fragment {
    private ViewRecordViewModel viewModel;
    private ViewNoteFragmentBinding binding;

    public static ViewNoteFragment newInstance() {
        return new ViewNoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ViewNoteFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ViewRecordViewModel.class);
        viewModel.getRecord().observe(getViewLifecycleOwner(), new ActivityObserver<Record>(getActivity()) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Record record = data.getData();

                binding.name.setText(record.getFieldValue("name"));
                binding.note.setText(record.getFieldValue("note"));
            }
        });
    }
}
