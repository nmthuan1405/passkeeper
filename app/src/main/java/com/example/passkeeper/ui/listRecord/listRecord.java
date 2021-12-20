package com.example.passkeeper.ui.listRecord;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.R;
import com.example.passkeeper.databinding.ListRecordFragmentBinding;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

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
        speedDialFloatingButton(savedInstanceState == null);
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");



    }

    private void speedDialFloatingButton(boolean isAddActionItems) {
        SpeedDialView speedDialView = binding.speedDial;
        if (isAddActionItems){
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_password, R.drawable.ic_password)
                    .setLabel("Add Password")
                    .create());
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_card, R.drawable.ic_card)
                    .setLabel("Add Card")
                    .create());
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_secure_note, R.drawable.ic_note)
                    .setLabel("Add Secure Note")
                    .create());
        }

        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                Toast.makeText(getActivity(), actionItem.getLabel(getActivity())+" clicked", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}