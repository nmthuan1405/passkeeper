package com.example.passkeeper.ui.listGroup.listMemberGroup;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.model.Members;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityListMemberGroupBinding;
import com.example.passkeeper.ui.dialog.NewMemberDialog;
import com.example.passkeeper.ui.listGroup.ListGroupViewModel;
import com.example.passkeeper.ui.utils.ActivityObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListMemberGroupActivity extends AppCompatActivity implements NewMemberDialog.NewMemberDialogListener {

    private ListMemberGroupViewModel mViewModel;
    private ActivityListMemberGroupBinding binding;
    private MemberGroupAdapter mAdapter;
    private final String TAG = "@LMG_flag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMemberGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewModel = new ViewModelProvider(this).get(ListMemberGroupViewModel.class);
        int id = getIntent().getIntExtra("id", -1);
        Log.i(TAG, "List member group, id = " + id);
        mViewModel.setId(id);

        initRecyclerView();
        initFloatingActionButton();
        initDeleteGroup();
    }

    private void initDeleteGroup() {
        AppCompatActivity activity = this;
        binding.deleteGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Delete group in Member List Group
                System.out.println("Delete group");
                ListGroupViewModel listGroupViewModel = new ListGroupViewModel();
                listGroupViewModel.deleteGroup(mViewModel.getId()).observe(activity, new ActivityObserver<List<Group>>(activity) {
                    @Override
                    public void onSuccess(Resource<List<Group>> data) {
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecycleView();
    }

    private void initRecyclerView() {
        mAdapter = new MemberGroupAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);

        updateRecycleView();
    }

    public void updateRecycleView() {
        mViewModel.getGroup().observe(this, new ActivityObserver<Group>(this) {
            @Override
            public void onSuccess(Resource<Group> data) {
                List<Members> allMembers = data.getData().getOwnersAndMembers();
                System.out.println(allMembers.size());
                if (allMembers != null) {
                    Log.i(TAG, "List member data changed, size = " + allMembers.size());
                    mAdapter.setListMember(allMembers);
                }
            }
        });

        /*int members_size = mAdapter.getItemCount();
        for (int i = 0; i < members_size; i++) {
            mAdapter.notifyItemChanged(i);
        }

         */
    }


    private void initFloatingActionButton() {
        FloatingActionButton floatingButton = binding.floatingActionButton;

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void openDialog() {
        NewMemberDialog newMemberDialog = new NewMemberDialog();
        newMemberDialog.show(getSupportFragmentManager(), "new member dialog");
    }

    @Override
    public void applyResult(String memberEmail) {
        mViewModel.addMember(memberEmail).observe(this, new ActivityObserver<Group>(this) {
            @Override
            public void onSuccess(Resource<Group> data) {
                updateRecycleView();
            }
        });
    }
}