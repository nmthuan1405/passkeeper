package com.example.passkeeper.ui.listMemberGroup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ItemMemberGroupBinding;
import com.example.passkeeper.ui.utils.ActivityObserver;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MemberGroupAdapter extends RecyclerView.Adapter<MemberGroupAdapter.MemberGroupViewHolder> {

    private final ListMemberGroupActivity activity;
    private ItemMemberGroupBinding binding;
    private List<Members> mListMemberGroup = null;

    public MemberGroupAdapter(ListMemberGroupActivity a) {
        activity = a;
    }

    @NonNull
    @Override
    public MemberGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMemberGroupBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MemberGroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberGroupViewHolder holder, int position) {
        Members memberGroup = mListMemberGroup.get(position);
        holder.binding.nameMemberTextView.setText(memberGroup.getEmail());
        if (memberGroup.isOwner()) {
            holder.binding.ownerToggle.performClick();
        }
        holder.binding.deleteButton.setOnClickListener(v -> {
            System.out.println("Delete member");
            ListMemberGroupViewModel viewModel = new ListMemberGroupViewModel();
            viewModel.setId(memberGroup.getGroupId());
            viewModel.deleteMember(memberGroup.getEmail()).observe(activity, new ActivityObserver<List<Members>>(activity) {
                @Override
                public void onSuccess(Resource<List<Members>> data) {
                    activity.updateRecycleView();
                }
            });
        });
    }


    @Override
    public int getItemCount() {
        if (mListMemberGroup != null)
            return mListMemberGroup.size();
        return 0;
    }


    public void setListMember(List<Members> allMembers) {
        if (allMembers != null) {
            mListMemberGroup = allMembers;
            notifyDataSetChanged();
        }
    }

    public static class MemberGroupViewHolder extends RecyclerView.ViewHolder{
        private final ItemMemberGroupBinding binding;

        public MemberGroupViewHolder(@NotNull ItemMemberGroupBinding itemBinding){
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
