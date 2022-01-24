package com.example.passkeeper.ui.record.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityViewRecordBinding;
import com.example.passkeeper.ui.listGroup.ListGroupViewModel;
import com.example.passkeeper.ui.record.edit.EditRecordActivity;
import com.example.passkeeper.ui.record.view.fragment.ViewCardFragment;
import com.example.passkeeper.ui.record.view.fragment.ViewNoteFragment;
import com.example.passkeeper.ui.record.view.fragment.ViewPasswordFragment;
import com.example.passkeeper.ui.utils.ActivityObserver;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class ViewRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "@@VR_Act";

    private ViewRecordViewModel viewModel;
    private ActivityViewRecordBinding binding;
    private boolean is_fav;
    private Menu actionMenu;
    private List<Integer> sharedGroupId;

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

        updateRecord();
    }

    public void updateRecord() {
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

                sharedGroupId = new ArrayList<>();
                for (String sid :
                        record.getGroups()) {
                    sharedGroupId.add(Integer.parseInt(sid));
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
            case R.id.share_group:
                ListGroupViewModel groupViewModel = new ListGroupViewModel();
                Activity context = this;

                groupViewModel.fetchOwnedGroups().observe(this, new ActivityObserver<List<Group>>(this) {
                    @Override
                    public void onSuccess(Resource<List<Group>> data) {
                        List<Group> groups = data.getData();
                        Integer groupsSize = groups.size();
                        String[] names = new String[groupsSize];
                        Integer[] ids = new Integer[groupsSize];
                        boolean[] checked = new boolean[groupsSize];
                        if (groups != null) {
                            Log.i(TAG, "List group data changed, size = " + groups.size());
                            for (int i = 0; i < groupsSize; i++) {
                                Group group = groups.get(i);
                                names[i] = group.getName();
                                ids[i] = group.getId();
                                checked[i] = sharedGroupId.contains(ids[i]);
                            }

                        }

                        new MaterialAlertDialogBuilder(context)
                                .setTitle("Choose shared groups")
                                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        List<Integer> shareIds = new ArrayList<>();
                                        List<Integer> unshareIds = new ArrayList<>();

                                        for (int i = 0; i < groupsSize; i++) {
                                            int groupId = ids[i];
                                            if (checked[i]) {
                                                shareIds.add(groupId);
                                            } else {
                                                unshareIds.add(groupId);
                                            }
                                        }

                                        System.out.println("sharing");
                                        viewModel.share_unshare(shareIds, unshareIds).observe((LifecycleOwner) context, new ActivityObserver<Void>(context) {
                                            @Override
                                            public void onSuccess(Resource<Void> resource) {
                                                updateRecord();
                                            }
                                        });
                                    }
                                })
                                .setMultiChoiceItems(names, checked, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        System.out.println(names[which]);
                                    }

                                })
                                .show();
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