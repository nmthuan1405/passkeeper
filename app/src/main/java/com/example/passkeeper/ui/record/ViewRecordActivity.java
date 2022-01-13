package com.example.passkeeper.ui.record;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityViewRecordBinding;
import com.example.passkeeper.ui.record.fragment.ViewCardFragment;
import com.example.passkeeper.ui.record.fragment.ViewNoteFragment;
import com.example.passkeeper.ui.record.fragment.ViewPasswordFragment;
import com.example.passkeeper.ui.utils.ActivityObserver;

public class ViewRecordActivity extends AppCompatActivity {
    private final String TAG = "@@VR_Act";

    private RecordViewModel viewModel;
    private ActivityViewRecordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(RecordViewModel.class);

        int id = getIntent().getIntExtra("id", -1);
        Log.i(TAG, "View record, id = " + id);

        viewModel.fetchRecord(id).observe(this, new ActivityObserver<Record>(this) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Record record = data.getData();
                switch (record.getType()) {
                    case "password":
                        loadFragment(ViewPasswordFragment.newInstance());
                        break;
                    case "card":
                        loadFragment(ViewCardFragment.newInstance());
                        break;
                    case "note":
                        loadFragment(ViewNoteFragment.newInstance());
                        break;
                    default:
                        finish();
                        break;
                }

                viewModel.getRecord().removeObserver(this);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_record_fragment, fragment)
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
}