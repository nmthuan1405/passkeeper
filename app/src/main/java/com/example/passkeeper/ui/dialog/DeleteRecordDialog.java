package com.example.passkeeper.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class DeleteRecordDialog extends DialogFragment {
    private OnDeleteRecordListener onDeleteRecordListener;

    public interface OnDeleteRecordListener {
        void onDeleteRecord();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage("Do you want to delete this record?")
                .setPositiveButton("Yes", (dialog, id) -> {
                    onDeleteRecordListener.onDeleteRecord();
                })
                .setNegativeButton("No", (dialog, id) -> {});
        return builder.create();
    }

    public void setOnDeleteRecordListener(OnDeleteRecordListener listener) {
        onDeleteRecordListener = listener;
    }
}
