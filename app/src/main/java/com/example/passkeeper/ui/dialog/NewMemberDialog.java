package com.example.passkeeper.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.passkeeper.R;
import com.example.passkeeper.databinding.NewAddDialogBinding;

public class NewMemberDialog extends AppCompatDialogFragment {
    private EditText edtMemberName;
    private NewMemberDialogListener listener;
    private NewAddDialogBinding binding;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_add_dialog, null);

        binding.name.setText("Member name");


        builder.setView(view)
                .setTitle("New Member")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String memberName = edtMemberName.getText().toString();
                        listener.applyResult(memberName);
                    }
                });

        edtMemberName = view.findViewById(R.id.edtName);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NewMemberDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement NewMemberDialogListener");
        }
    }

    public interface NewMemberDialogListener{
        void applyResult(String memberName);
    }
}

