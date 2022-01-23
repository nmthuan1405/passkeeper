package com.example.passkeeper.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.passkeeper.R;

public class NewMemberDialog extends AppCompatDialogFragment {
    private EditText edtMemberEmail;
    private NewMemberDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_member_dialog, null);

        builder.setView(view)
                .setTitle("Add Member")
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .setPositiveButton("OK", (dialog, which) -> {
                    String memberEmail = edtMemberEmail.getText().toString();
                    listener.applyResult(memberEmail);
                });

        edtMemberEmail = view.findViewById(R.id.edtMemberEmail);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NewMemberDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement AddGroupMemberDialogListener");
        }
    }

    public interface NewMemberDialogListener{
        void applyResult(String memberEmail);
    }
}