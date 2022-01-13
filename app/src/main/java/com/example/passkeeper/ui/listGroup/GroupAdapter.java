package com.example.passkeeper.ui.listGroup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.model.MemberGroup;
import com.example.passkeeper.databinding.ItemGroupBinding;
import com.example.passkeeper.databinding.ItemMemberGroupBinding;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Member;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<com.example.passkeeper.ui.listGroup.GroupAdapter.GroupViewHolder> {

    private List<Group> mListGroup = null;
    private ItemGroupBinding binding;

    @NonNull
    @Override
    public com.example.passkeeper.ui.listGroup.GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemGroupBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new com.example.passkeeper.ui.listGroup.GroupAdapter.GroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Group group = mListGroup.get(position);

        // TODO: Set group data to UI
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
        private ItemGroupBinding binding;

        public GroupViewHolder(@NotNull ItemGroupBinding itemBinding){
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
