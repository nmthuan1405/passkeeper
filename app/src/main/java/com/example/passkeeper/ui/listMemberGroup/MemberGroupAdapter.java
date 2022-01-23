package com.example.passkeeper.ui.listMemberGroup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.databinding.ItemMemberGroupBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MemberGroupAdapter extends RecyclerView.Adapter<MemberGroupAdapter.MemberGroupViewHolder> {

    private List<String> mListMemberGroup = null;
    private ItemMemberGroupBinding binding;

    @NonNull
    @Override
    public MemberGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMemberGroupBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MemberGroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberGroupViewHolder holder, int position) {
        String memberGroup = mListMemberGroup.get(position);
        holder.binding.nameMemberTextView.setText(memberGroup);
    }

    @Override
    public int getItemCount() {
        if (mListMemberGroup != null)
            return mListMemberGroup.size();
        return 0;
    }


    public void setListMember(List<String> allMembers) {
        if (allMembers != null){
            mListMemberGroup = allMembers;
            notifyDataSetChanged();
        }
    }

    public static class MemberGroupViewHolder extends RecyclerView.ViewHolder{
        private ItemMemberGroupBinding binding;

        public MemberGroupViewHolder(@NotNull ItemMemberGroupBinding itemBinding){
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
