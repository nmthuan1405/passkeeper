package com.example.passkeeper.ui.record.add;

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
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityAddRecordBinding;
import com.example.passkeeper.ui.record.add.fragment.AddCardFragment;
import com.example.passkeeper.ui.record.add.fragment.AddNoteFragment;
import com.example.passkeeper.ui.record.add.fragment.AddPasswordFragment;
import com.example.passkeeper.ui.record.add.fragment.AddRecordFragment;
import com.example.passkeeper.ui.utils.ActivityObserver;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "@@AR_Act";

    private AddRecordViewModel viewModel;
    private ActivityAddRecordBinding binding;
    private AddRecordFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle("Add");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.saveBtn.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(AddRecordViewModel.class);

        String type = getIntent().getStringExtra("type");
        Log.i(TAG, "Create new record, type = " + type);
        viewModel.setType(type);

        switch (type) {
            case "password":
                fragment = AddPasswordFragment.newInstance();
                break;
            case "card":
                fragment = AddCardFragment.newInstance();
                break;
            case "note":
                fragment = AddNoteFragment.newInstance();
                break;
            default:
                Log.e(TAG, "Type not found, finish activity!");
                finish();
        }

        loadFragment(fragment);
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
        RecordFieldList fieldList = fragment.getFieldList();
        viewModel.addRecord(fieldList).observe(this, new ActivityObserver<Record>(this) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Log.i(TAG, "Create record success!");
                finish();
            }
        });
    }
}