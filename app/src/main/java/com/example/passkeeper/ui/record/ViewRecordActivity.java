package com.example.passkeeper.ui.record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.NoteRecordBinding;
import com.example.passkeeper.ui.listRecord.ListRecordViewModel;
import com.example.passkeeper.ui.utils.ActivityObserver;

public class ViewRecordActivity extends AppCompatActivity {
    private final String TAG = "@@VR_Act";

    private RecordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(RecordViewModel.class);

        int id = getIntent().getIntExtra("id", -1);
        Log.i(TAG, "View record, id = " + id);

        viewModel.getRecord(id).observe(this, new ActivityObserver<Record>(this) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Record record = data.getData();
                switch (record.getType()) {
                    case "password":
                        onPasswordRecord(record);
                        break;
                    case "card":
                        onCardRecord(record);
                        break;
                    case "note":
                        onNoteRecord(record);
                        break;
                    default:
                        finish();
                        break;
                }
            }
        });
    }

    private void onNoteRecord(Record record) {
        Log.i(TAG, "Load note record...");
    }

    private void onCardRecord(Record record) {
        Log.i(TAG, "Load card record...");
    }

    private void onPasswordRecord(Record record) {
        Log.i(TAG, "Load password record...");
    }
}