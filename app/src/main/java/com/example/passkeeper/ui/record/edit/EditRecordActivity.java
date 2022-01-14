package com.example.passkeeper.ui.record.edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.EditRecordRequest;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityEditRecordBinding;
import com.example.passkeeper.ui.record.edit.fragment.EditCardFragment;
import com.example.passkeeper.ui.record.edit.fragment.EditNoteFragment;
import com.example.passkeeper.ui.record.edit.fragment.EditPasswordFragment;
import com.example.passkeeper.ui.record.edit.fragment.EditRecordFragment;
import com.example.passkeeper.ui.utils.ActivityObserver;

public class EditRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "@@ER_Act";

    private EditRecordViewModel viewModel;
    private ActivityEditRecordBinding binding;
    private EditRecordFragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.saveBtn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(EditRecordViewModel.class);

        int id = getIntent().getIntExtra("id", -1);
        Log.i(TAG, "Edit record, id = " + id);

        viewModel.fetchRecord(id).observe(this, new ActivityObserver<Record>(this) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Record record = data.getData();
                switch (record.getType()) {
                    case "password":
                        fragment = EditPasswordFragment.newInstance();
                        break;
                    case "card":
                        fragment = EditCardFragment.newInstance();
                        break;
                    case "note":
                        fragment = EditNoteFragment.newInstance();
                        break;
                    default:
                        finish();
                        break;
                }
                loadFragment(fragment);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.edit_record_fragment, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.record_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        if (fragment != null) {
            EditRecordRequest request = fragment.getEditRequest();

            viewModel.editRecord(request).observe(this, new ActivityObserver<Record>(this) {
                @Override
                public void onSuccess(Resource<Record> data) {
                    Log.i(TAG, "Edit success, finish activity!");
                    finish();
                }
            });
        }
    }
}