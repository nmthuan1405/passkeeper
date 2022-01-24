package com.example.passkeeper.ui.record.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityViewRecordBinding;
import com.example.passkeeper.ui.record.edit.EditRecordActivity;
import com.example.passkeeper.ui.record.view.fragment.ViewCardFragment;
import com.example.passkeeper.ui.record.view.fragment.ViewNoteFragment;
import com.example.passkeeper.ui.record.view.fragment.ViewPasswordFragment;
import com.example.passkeeper.ui.utils.ActivityObserver;

public class ViewRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "@@VR_Act";

    private ViewRecordViewModel viewModel;
    private ActivityViewRecordBinding binding;
    private boolean is_fav;
    private Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.editBtn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(ViewRecordViewModel.class);

        int id = getIntent().getIntExtra("id", -1);
        Log.i(TAG, "View record, id = " + id);
        viewModel.setId(id);

        viewModel.getRecord().observe(this, new ActivityObserver<Record>(this) {
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

                is_fav = record.isFavorite();
                if (is_fav) {
                    System.out.println("change");
                    actionMenu.findItem(R.id.favorite).setIcon(R.drawable.ic_filled_star);
                }

                viewModel.getRecord().removeObserver(this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.fetchRecord();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_record_fragment, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.record_menu, menu);
        actionMenu = menu;

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                is_fav = !is_fav;
                viewModel.changeFavoriteStatus(is_fav).observe(this, new ActivityObserver<Record>(this) {
                    @Override
                    public void onSuccess(Resource<Record> data) {
                        if (is_fav) {
                            item.setIcon(R.drawable.ic_filled_star);
                        } else {
                            item.setIcon(R.drawable.ic_star_white);
                        }
                    }
                });
                break;
            case R.id.delete_record:
                viewModel.deleteRecord().observe(this, new ActivityObserver<Void>(this) {
                    @Override
                    public void onSuccess(Resource<Void> resource) {
                        finish();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, EditRecordActivity.class);
        intent.putExtra("id", viewModel.getId());
        startActivity(intent);
    }
}