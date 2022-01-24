package com.example.passkeeper.ui.shareGroup.listRecordShareGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.ItemRecordGroupBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecordGroupAdapter extends RecyclerView.Adapter<RecordGroupAdapter.RecordGroupViewHolder>{

    private ListRecordShareGroupActivity activity;
    private ItemRecordGroupBinding binding;
    private List<Record> mListRecordGroup = null;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Record record);
    }

    public RecordGroupAdapter(ListRecordShareGroupActivity a){this.activity = a;}

    @NonNull
    @Override
    public RecordGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecordGroupBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordGroupAdapter.RecordGroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordGroupViewHolder holder, int position) {
        Record record = mListRecordGroup.get(position);
        holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(record));
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        if (mListRecordGroup != null)
            return mListRecordGroup.size();
        return 0;
    }

    public void setListRecordGroup(List<Record> records) {
        if (records != null) {
            mListRecordGroup = records;
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class RecordGroupViewHolder extends RecyclerView.ViewHolder{
        private final ItemRecordGroupBinding binding;

        public RecordGroupViewHolder(@NotNull ItemRecordGroupBinding itemBinding){
            super(itemBinding.getRoot());
            binding = itemBinding;
        }

        public void bind(Record record) {
            String name = record.getFieldValue("name");
            if (name != null) {
                binding.nameRecordTextView.setText(name);
            } else {
                binding.nameRecordTextView.setText("");
            }

            String description = record.getDescription();
            if (description != null) {
                binding.descriptionRecordTextView.setText(description);
                binding.descriptionRecordTextView.setVisibility(View.VISIBLE);
            } else {
                binding.descriptionRecordTextView.setVisibility(View.GONE);
            }

            Integer icon = getIconId(record.getType());
            if (icon != null) {
                binding.iconTypeRecord.setImageResource(icon);
            }

            Boolean isFavorite = record.isFavorite();
            if (isFavorite){
                binding.favoriteImage.setImageResource(R.drawable.ic_fill_star);
            } else {
                binding.favoriteImage.setImageResource(R.drawable.ic_star);
            }

        }

        private Integer getIconId(String type) {
            switch (type) {
                case "password":
                    return R.drawable.ic_password;
                case "card":
                    return R.drawable.ic_card;
                case "note":
                    return R.drawable.ic_note;
                default:
                    return null;
            }
        }
    }
}
