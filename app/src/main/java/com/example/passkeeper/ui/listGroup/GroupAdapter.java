package com.example.passkeeper.ui.listGroup;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ItemGroupBinding;
import com.example.passkeeper.ui.listMemberGroup.ListMemberGroupActivity;
import com.example.passkeeper.ui.utils.ActivityObserver;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<com.example.passkeeper.ui.listGroup.GroupAdapter.GroupViewHolder> {

    private List<Group> mListGroup = null;
    private ItemGroupBinding binding;
    private final ListGroupViewModel listGroupViewModel;
    private final ListGroupActivity activity;

    public GroupAdapter(ListGroupActivity activity) {
        this.activity = activity;
        listGroupViewModel = new ListGroupViewModel();
    }

    @NonNull
    @Override
    public com.example.passkeeper.ui.listGroup.GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemGroupBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new com.example.passkeeper.ui.listGroup.GroupAdapter.GroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Group group = mListGroup.get(position);
        String name = group.getName();
        holder.binding.nameGroupTextView.setText(name);
        holder.binding.nameGroupTextView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ListMemberGroupActivity.class);
            intent.putExtra("id", group.getId());
            view.getContext().startActivity(intent);
        });
        holder.binding.deleteButton.setOnClickListener(view -> {
            Integer groupId = group.getId();

            listGroupViewModel.deleteGroup(groupId).observe(activity, new ActivityObserver<List<Group>>(this.activity) {
                @Override
                public void onSuccess(Resource<List<Group>> data) {
                    activity.updateRecycleView();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        if (mListGroup != null)
            return mListGroup.size();
        return 0;
    }

    public void setListGroup(List<Group> listGroup){
        if (listGroup != null){
            mListGroup = listGroup;
            notifyDataSetChanged();
        }
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder{
        private final ItemGroupBinding binding;

        public GroupViewHolder(@NotNull ItemGroupBinding itemBinding){
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
