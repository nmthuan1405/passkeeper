package com.example.passkeeper.ui.shareGroup;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.databinding.ItemShareGroupBinding;

import com.example.passkeeper.ui.listMemberGroup.ListMemberGroupActivity;
import com.example.passkeeper.ui.shareGroup.listRecordShareGroup.ListRecordShareGroupActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShareGroupAdapter extends RecyclerView.Adapter<ShareGroupAdapter.ShareGroupViewHolder> {

    private List<Group> mListGroup = null;
    private ItemShareGroupBinding binding;
    private final ShareGroupViewModel viewModel;
    private final ShareGroupActivity activity;

    public ShareGroupAdapter(ShareGroupActivity activity) {
        this.activity = activity;
        viewModel = new ShareGroupViewModel();
    }

    @NonNull
    @Override
    public com.example.passkeeper.ui.shareGroup.ShareGroupAdapter.ShareGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemShareGroupBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new com.example.passkeeper.ui.shareGroup.ShareGroupAdapter.ShareGroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.passkeeper.ui.shareGroup.ShareGroupAdapter.ShareGroupViewHolder  holder, int position) {
        Group group = mListGroup.get(position);
        String name = group.getName();
        holder.binding.nameGroupTextView.setText(name);
        holder.binding.nameGroupTextView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ListRecordShareGroupActivity.class);
            intent.putExtra("id", group.getId());
            view.getContext().startActivity(intent);
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

    public static class ShareGroupViewHolder extends RecyclerView.ViewHolder{
        private final ItemShareGroupBinding binding;

        public ShareGroupViewHolder(@NotNull ItemShareGroupBinding itemBinding){
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
