package com.example.passkeeper.ui.listMemberGroup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.data.model.MemberGroup;
import com.example.passkeeper.databinding.ItemGroupBinding;
import com.example.passkeeper.databinding.ItemMemberGroupBinding;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Member;
import java.util.List;

public class MemberGroupAdapter extends RecyclerView.Adapter<MemberGroupAdapter.MemberGroupViewHolder> {

    private List<MemberGroup> mListMemberGroup = null;
    private ItemMemberGroupBinding binding;

    @NonNull
    @Override
    public MemberGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMemberGroupBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MemberGroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberGroupViewHolder holder, int position) {
        MemberGroup memberGroup = mListMemberGroup.get(position);

        // TODO: Set member group data to UI
    }

    @Override
    public int getItemCount() {
        if (mListMemberGroup != null)
            return mListMemberGroup.size();
        return 0;
    }

    public void setListMemberGroup(List<MemberGroup> listMemberGroup){
        if (listMemberGroup != null){
            mListMemberGroup = listMemberGroup;
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
