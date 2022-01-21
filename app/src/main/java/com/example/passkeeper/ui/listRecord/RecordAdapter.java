package com.example.passkeeper.ui.listRecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.ItemRecordBinding;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    private List<Record> mListRecord = null;
    private OnCheckedListener onFavoriteCheckedListener;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemLongClickListener;

    public interface OnCheckedListener {
        void onChecked(Record record, int position, boolean isChecked);
    }
    public interface OnItemClickListener {
        void onItemClick(Record record, int position);
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecordBinding binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record record = mListRecord.get(position);

        holder.binding.favoriteToggle.setOnClickListener(view -> {
            boolean isChecked = holder.binding.favoriteToggle.isChecked();
            onFavoriteCheckedListener.onChecked(record, position, isChecked);
        });
        holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(record, position));
        holder.itemView.setOnLongClickListener(view -> {
            onItemLongClickListener.onItemClick(record, position);
            return true;
        });

        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        if (mListRecord != null)
            return mListRecord.size();
        return 0;
    }

    public void setListRecord(List<Record> listRecord) {
        if (listRecord != null) {
            mListRecord = listRecord;
            notifyDataSetChanged();
        }
    }

    public void editRecord(Record record, int position) {
        if (record != null) {
            mListRecord.set(position, record);
            notifyItemChanged(position, record);
        }
    }

    public void deleteRecord(int position) {
        mListRecord.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnFavoriteCheckedListener(OnCheckedListener onFavoriteClickListener) {
        this.onFavoriteCheckedListener = onFavoriteClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        public final ItemRecordBinding binding;

        public RecordViewHolder(@NonNull ItemRecordBinding itemBinding) {
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

            binding.favoriteToggle.setChecked(record.isFavorite());
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
